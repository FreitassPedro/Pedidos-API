package com.pedro.pedidosApi.service;

import com.pedro.pedidosApi.entity.Pedido;
import com.pedro.pedidosApi.entity.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PedidoService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /* O RabbitTemplate é uma classe que facilita a comunicação com o RabbitMQ
     Ele fornece métodos para enviar e receber mensagens
     */
    
    private final RabbitTemplate rabbitTemplate;

    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public Pedido enfileirarPedido(Pedido pedido) {
        pedido.setStatus(Status.EM_PROCESSAMENTO);
        try {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, pedido);
        log.info("Enfileirando pedido: {}", pedido.toString());
        } catch (Exception e) {
            log.error("Erro ao enfileirar pedido: {}", e.getMessage());
        }
        return pedido;
    }
}
