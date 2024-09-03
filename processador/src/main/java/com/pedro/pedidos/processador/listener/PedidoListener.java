package com.pedro.pedidos.processador.listener;

import com.pedro.pedidos.processador.entity.Pedido;
import com.pedro.pedidos.processador.entity.enums.Status;
import com.pedro.pedidos.processador.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PedidoListener {

    private final PedidoService pedidoService;

    public PedidoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);

        pedidoService.salvarPedido(pedido);
    }
}
