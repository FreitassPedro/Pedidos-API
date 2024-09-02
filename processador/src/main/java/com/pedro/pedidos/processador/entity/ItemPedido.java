package com.pedro.pedidos.processador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    private Produto produto;
    private int quantidade;

    @ManyToOne
    private Pedido pedido;
}
