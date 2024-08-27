package com.pedro.pedidosApi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro.pedidosApi.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID id = UUID.randomUUID();
    private String cliente;

    private Integer valorTotal;
    private String emailNotificacao;

    private List<ItemPedido> itemPedidos;

    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

}
