package com.fiap.orderhub.interfaces;

import com.fiap.orderhub.dto.PedidoDTO;

public interface IPedidoGateway {
    void produceOnQueue(PedidoDTO pedido);
}
