package com.pedro.pedidosApi.controller;

import com.pedro.pedidosApi.entity.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
         log.info("Criando pedido: {}", pedido.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
