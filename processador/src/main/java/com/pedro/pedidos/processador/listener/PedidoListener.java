package com.pedro.pedidos.processador.listener;

import com.pedro.pedidos.processador.entity.Pedido;
import com.pedro.pedidos.processador.entity.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class PedidoListener {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);
        log.info("Pedido recebido: {}", pedido.getId());
    }
}
