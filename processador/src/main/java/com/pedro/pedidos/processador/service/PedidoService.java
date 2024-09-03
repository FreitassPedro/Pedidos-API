package com.pedro.pedidos.processador.service;

import com.pedro.pedidos.processador.entity.ItemPedido;
import com.pedro.pedidos.processador.entity.Pedido;
import com.pedro.pedidos.processador.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }


    public void salvarPedido(Pedido pedido) {
        // Nesse sistema, não temos o Produto salvo no banco, entao vamos salvar ele a cada requisição
        // Em um sistema real, o Produto já estaria salvo no banco e não precisaria ser salvo, apenas o Pedido e os itensPedido
        produtoService.saveAll(pedido.getItens());

        List<ItemPedido> itemPedidos = itemPedidoService.saveAll(pedido.getItens());

;
        pedidoRepository.save(pedido);

        itemPedidoService.updatedItemPedido(itemPedidos, pedido);

        log.info("Pedido salvo: {}", pedido.getId());
    }
}
