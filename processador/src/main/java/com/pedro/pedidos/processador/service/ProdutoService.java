package com.pedro.pedidos.processador.service;

import com.pedro.pedidos.processador.entity.ItemPedido;
import com.pedro.pedidos.processador.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void saveAll(List<ItemPedido> itens) {
        itens.forEach(item -> {
            produtoRepository.save(item.getProduto());
        });
    }
}
