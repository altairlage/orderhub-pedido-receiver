package com.fiap.orderhub.interfaces.implementations;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.adapters.producers.RabbitMQProducer;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.springframework.stereotype.Component;

@Component
public class IPedidoGatewayImpl implements IPedidoGateway {
    private final RabbitMQProducer rabbitMQProducer;

    public IPedidoGatewayImpl(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Override
    public void produceOnQueue(CriarPedidoDTO pedido) {
        rabbitMQProducer.sendMessage(pedido);
    }
}
