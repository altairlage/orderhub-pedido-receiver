package com.fiap.orderhub.adapters.controllers;

import br.com.orderhub.core.domain.enums.StatusPedido;
import br.com.orderhub.core.dto.pedidos.CriarPedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.orderhub.interfaces.IPedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PedidoAPIControllerTest {
    private MockMvc mockMvc;
    private IPedidoGateway pedidoGateway;
    private PedidoReceiverAPIController controllerAPI;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CriarPedidoDTO criarPedidoDTO;
    private Map<String, Object> mapProduto1;
    private Map<String, Object> mapProduto2;

    @BeforeEach
    public void setup() {
        pedidoGateway = mock(IPedidoGateway.class);
        controllerAPI = new PedidoReceiverAPIController(pedidoGateway);

        mockMvc = MockMvcBuilders.standaloneSetup(controllerAPI).build();

        mapProduto1 = new HashMap<>();
        mapProduto1.put("quantidade", 1);
        mapProduto1.put("idProduto", 1);

        mapProduto2 = new HashMap<>();
        mapProduto2.put("quantidade", 2);
        mapProduto2.put("idProduto", 2);

        criarPedidoDTO = new CriarPedidoDTO(1L, Arrays.asList(mapProduto1, mapProduto2), StatusPedido.ABERTO);
    }

    @Test
    void deveReceberPedidoComSucesso() throws Exception {
        String json = objectMapper.writeValueAsString(criarPedidoDTO);

        mockMvc.perform(post("/pedido-receiver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
