package com.fiap.orderhub.adapters.controllers;

import com.fiap.orderhub.domain.usecases.ProduceOrderOnQueueUseCase;
import com.fiap.orderhub.dto.PedidoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedido-receiver")
public class PedidoReceiverAPIController {
    private final ProduceOrderOnQueueUseCase produceOrderOnQueueUseCase;

    public PedidoReceiverAPIController(ProduceOrderOnQueueUseCase produceOrderOnQueueUseCase) {
        this.produceOrderOnQueueUseCase = produceOrderOnQueueUseCase;
    }

    @PostMapping
    public ResponseEntity<String> produceOnQueue(@RequestBody PedidoDTO pedido) {
        produceOrderOnQueueUseCase.run(pedido);
        return ResponseEntity.ok("Seu pedido foi enviado com sucesso!");
    }
}
