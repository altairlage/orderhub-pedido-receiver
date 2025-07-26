package com.fiap.orderhub.adapters.producers;

import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.exceptions.ProduceOnQueueException;
import com.fiap.orderhub.infrastructure.config.RabbitMqProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RabbitMQProducerTest {
    @Mock
    RabbitTemplate rabbitTemplate;

    @Mock
    RabbitMqProperties properties;

    @InjectMocks
    RabbitMQProducer producer;

    @Test
    void deveEnviarMensagemParaFila() {
        Map<String, Object> mapProduto1 = new HashMap<>();
        mapProduto1.put("quantidade", 1);
        mapProduto1.put("idProduto", 1);

        Map<String, Object> mapProduto2 = new HashMap<>();
        mapProduto2.put("quantidade", 2);
        mapProduto2.put("idProduto", 2);

        CriarPedidoDTO dto = new CriarPedidoDTO(1L, Arrays.asList(mapProduto1, mapProduto2), StatusPedido.ABERTO);
        when(properties.getQueue()).thenReturn("pedido-receiver-queue");

        producer.sendMessage(dto);

        verify(rabbitTemplate).convertAndSend("pedido-receiver-queue", dto);
    }

    @Test
    void deveLancarProduceOnQueueException() {
        assertThrows(ProduceOnQueueException.class, () -> {
            throw new ProduceOnQueueException("Erro ao produzir");
        });
    }

}
