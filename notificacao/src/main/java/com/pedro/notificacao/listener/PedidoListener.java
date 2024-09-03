package com.pedro.notificacao.listener;

import com.pedro.notificacao.entity.Pedido;
import com.pedro.notificacao.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PedidoListener {

    private final EmailService emailService;

    public PedidoListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void enviarNotificacao(Pedido pedido) {
        if (pedido.getValorTotal() > 1000) {
            log.error("Valor do pedido muito alto");
            throw new RuntimeException("Valor do pedido muito alto");
        }
        emailService.enviarEmail(pedido);
        log.info("Notificação gerada para o pedido: {}", pedido.getId());
    }

}
