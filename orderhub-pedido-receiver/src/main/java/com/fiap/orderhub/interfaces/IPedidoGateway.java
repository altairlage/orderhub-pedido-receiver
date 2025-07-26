package com.fiap.orderhub.interfaces;


import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;

public interface IPedidoGateway {
    void produceOnQueue(CriarPedidoDTO pedido);
}
