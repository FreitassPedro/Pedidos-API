package com.pedro.pedidosApi.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/send")
    public String sendTestMessage() {
        try {
            rabbitTemplate.convertAndSend(exchangeName, "", "Test message from Spring Boot!");
            return "Message sent successfully!";
        } catch (Exception e) {
            return "Error sending message: " + e.getMessage();
        }
    }
}