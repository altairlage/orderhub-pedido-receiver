package com.fiap.orderhub.adapters.producers;

import com.fiap.orderhub.dto.PedidoDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private KafkaTemplate<String, PedidoDTO> kafkaTemplate;

    public void sendKafkaMessage(PedidoDTO pedido) {
        kafkaTemplate.send("pedido-queue", pedido);
    }
}
