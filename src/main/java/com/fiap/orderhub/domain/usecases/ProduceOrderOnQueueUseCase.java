package com.fiap.orderhub.domain.usecases;

import com.fiap.orderhub.dto.PedidoDTO;
import com.fiap.orderhub.exceptions.ProduceOnQueueException;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.springframework.stereotype.Service;

@Service
public class ProduceOrderOnQueueUseCase {
    private final IPedidoGateway pedidoGateway;

    public ProduceOrderOnQueueUseCase(IPedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    public void run(PedidoDTO pedido) {
        try{
            pedidoGateway.produceOnQueue(pedido);
        } catch(Exception e){
            throw new ProduceOnQueueException("Was not possible to produce on queue. Error: " + e.getMessage());
        }

    }
}
