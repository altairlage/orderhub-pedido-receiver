package com.fiap.orderhub.domain.usecases;

import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fiap.orderhub.exceptions.ProduceOnQueueException;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProduceOrderOnQueueUseCaseTest {
    private IPedidoGateway pedidoGateway;
    private ProduceOrderOnQueueUseCase useCase;

    private CriarPedidoDTO pedidoFake;

    @BeforeEach
    void setup() {
        pedidoGateway = mock(IPedidoGateway.class);
        useCase = new ProduceOrderOnQueueUseCase(pedidoGateway);

        pedidoFake = new CriarPedidoDTO(123L, List.of(
                Map.of("quantidade", 1, "idProduto", 1),
                Map.of("quantidade", 2, "idProduto", 2)
        ), StatusPedido.ABERTO);
    }

    @Test
    void deveExecutarRunSemExcecaoQuandoGatewayFunciona() {
        assertDoesNotThrow(() -> useCase.run(pedidoFake));
        verify(pedidoGateway, times(1)).produceOnQueue(pedidoFake);
    }

    @Test
    void deveLancarProduceOnQueueExceptionQuandoGatewayFalha() {
        doThrow(new RuntimeException("Erro interno")).when(pedidoGateway).produceOnQueue(any());

        ProduceOnQueueException exception = assertThrows(ProduceOnQueueException.class, () -> {
            useCase.run(pedidoFake);
        });

        assertTrue(exception.getMessage().contains("Was not possible to produce on queue"));
        assertTrue(exception.getMessage().contains("Erro interno"));
    }
}
