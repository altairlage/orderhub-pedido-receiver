package com.fiap.orderhub.interfaces.implementations;

import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.adapters.producers.RabbitMQProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class IPedidoGatewayImplTest {
    private RabbitMQProducer rabbitMQProducer;
    private IPedidoGatewayImpl gatewayImpl;

    private CriarPedidoDTO criarPedidoDTO;

    @BeforeEach
    void setup() {
        rabbitMQProducer = mock(RabbitMQProducer.class);
        gatewayImpl = new IPedidoGatewayImpl(rabbitMQProducer);

        criarPedidoDTO = new CriarPedidoDTO(456L, List.of(
                Map.of("quantidade", 3, "idProduto", 99)
        ), StatusPedido.ABERTO);
    }

    @Test
    void deveDelegarEnvioDeMensagemAoRabbitMQProducer() {
        gatewayImpl.produceOnQueue(criarPedidoDTO);

        verify(rabbitMQProducer, times(1)).sendMessage(criarPedidoDTO);
    }
}
