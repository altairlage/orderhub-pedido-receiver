package com.fiap.orderhub.adapters.gateways;

import com.fiap.orderhub.adapters.producers.RabbitMQProducer;
import com.fiap.orderhub.dto.PedidoDTO;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.springframework.stereotype.Component;

@Component
public class PedidoGateway implements IPedidoGateway {

    private final RabbitMQProducer queueProducer;

    public PedidoGateway(RabbitMQProducer queueProducer) {
        this.queueProducer = queueProducer;
    }

    @Override
    public void produceOnQueue(PedidoDTO pedido) {
        queueProducer.sendMessage(pedido);
    }
}
