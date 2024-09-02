package com.pedro.pedidos.processador.repository;

import com.pedro.pedidos.processador.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
