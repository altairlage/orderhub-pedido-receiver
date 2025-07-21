package com.fiap.orderhub.adapters.producers;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.infrastructure.config.RabbitMqProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties properties;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate, RabbitMqProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }

    public void sendMessage(CriarPedidoDTO pedido) {
        rabbitTemplate.convertAndSend(properties.getQueue(), pedido);
    }
}
