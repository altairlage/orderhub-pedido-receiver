package com.fiap.orderhub.adapters.gateways;

import com.fiap.orderhub.adapters.producers.KafkaProducer;
import com.fiap.orderhub.dto.PedidoDTO;
import com.fiap.orderhub.interfaces.IPedidoGateway;

public class PedidoGateway implements IPedidoGateway {
    private final KafkaProducer kafkaProducer;

    public PedidoGateway(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void produceOnQueue(PedidoDTO pedido) {
        kafkaProducer.sendKafkaMessage(pedido);
    }
}
