package com.pedro.notificacao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pedro.notificacao.entity.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Pedido {

    private UUID id = UUID.randomUUID();
    private String cliente;

    private Integer valorTotal;
    private String emailNotificacao;

    private List<ItemPedido> itens = new ArrayList<>();

    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

}
