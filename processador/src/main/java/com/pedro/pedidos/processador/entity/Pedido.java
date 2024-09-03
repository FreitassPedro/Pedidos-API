package com.pedro.pedidos.processador.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.pedro.pedidos.processador.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();
    private String cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "email_notificacao")
    private String emailNotificacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();
}
