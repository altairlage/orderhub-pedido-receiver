package com.fiap.orderhub.adapters.producers;

import com.fiap.orderhub.dto.PedidoDTO;
import com.fiap.orderhub.infrastructure.config.RabbitMqProperties;
import lombok.Value;
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

    public void sendMessage(PedidoDTO pedido) {
        rabbitTemplate.convertAndSend(properties.getQueue(), pedido);
    }
}
