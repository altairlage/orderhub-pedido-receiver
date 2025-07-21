package com.fiap.orderhub.adapters.controllers;

import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.domain.usecases.ProduceOrderOnQueueUseCase;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedido-receiver")
public class PedidoReceiverAPIController {
    private final IPedidoGateway pedidoGateway;
    private final ProduceOrderOnQueueUseCase produceOrderOnQueueUseCase;

    public PedidoReceiverAPIController(IPedidoGateway gateway) {
        this.pedidoGateway = gateway;
        this.produceOrderOnQueueUseCase = new ProduceOrderOnQueueUseCase(gateway);
    }

    @PostMapping
    public ResponseEntity<String> produceOnQueue(@RequestBody CriarPedidoDTO pedido) {
        produceOrderOnQueueUseCase.run(pedido);
        return ResponseEntity.ok("Seu pedido foi enviado com sucesso!");
    }
}
