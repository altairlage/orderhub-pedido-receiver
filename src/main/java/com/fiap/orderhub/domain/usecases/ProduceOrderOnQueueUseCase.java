package com.fiap.orderhub.domain.usecases;

import com.fiap.orderhub.dto.PedidoDTO;
import com.fiap.orderhub.exceptions.ProduceOnQueueException;
import com.fiap.orderhub.interfaces.IPedidoGateway;

public class ProduceOrderOnQueueUseCase {
    private final IPedidoGateway pedidoGateway;

    public ProduceOrderOnQueueUseCase(IPedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public static ProduceOrderOnQueueUseCase create(IPedidoGateway pedidoGateway) {
        return new ProduceOrderOnQueueUseCase(pedidoGateway);
    }

    public void run(PedidoDTO pedido) {
        try{
            pedidoGateway.produceOnQueue(pedido);
        } catch(Exception e){
            throw new ProduceOnQueueException("Was not possible to produce on queue. Error: " + e.getMessage());
        }

    }
}
