package com.pedro.pedidosApi.service;

import com.pedro.pedidosApi.entity.Pedido;
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

    /* O RabbitTemplate é uma classe que facilita a comunicação com o RabbitMQ
     Ele fornece métodos para enviar e receber mensagens
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Pedido enfileirarPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(exchangeName, "", pedido);
        log.info("Enfileirando pedido: {}", pedido.toString());
        return pedido;
    }
}
