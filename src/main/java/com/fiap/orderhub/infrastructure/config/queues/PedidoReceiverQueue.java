package com.fiap.orderhub.infrastructure.config.queues;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class PedidoReceiverQueue {

    @Bean
    public Queue pedidoReceiverQueue() {
        return new Queue("pedido-receiver-queue", true);
    }
}
