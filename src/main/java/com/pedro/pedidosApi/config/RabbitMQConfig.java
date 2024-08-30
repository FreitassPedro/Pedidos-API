package com.pedro.pedidosApi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitMQConfig {

    // Definido no application.properties
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitPassword;

    // Criação de um exchange do tipo Fanout
    // Fanout: envia a mensagem para todas as filas que estão ligadas a ele
    @Bean
    public Exchange pedidosExchange() {
        Exchange exchange = new FanoutExchange(exchangeName);
        log.info("Teste............... Exchange criado: {}", exchangeName);
        return exchange;
    }

    // Criação de um RabbitAdmin para gerenciar as configurações do RabbitMQ
    // O RabbitAdmin é uma classe que facilita a configuração de elementos do RabbitMQ
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Configuração do MessageConverter para serializar e deserializar as mensagens
    // O Jackson2JsonMessageConverter é um MessageConverter que converte objetos para JSON
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Envia e recebe mensagens do RabbitMQ
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    /**
     * Configura o RabbitMQ imediatamente após a aplicação inicializar
     * 
     * @param rabbitAdmin
     * @return
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(
            RabbitAdmin rabbitAdmin, Exchange pedidosExchange) {
        return event -> {
            log.info(" ---------------------- RabbitAdmin inicializado...");
            try {
                rabbitAdmin.declareExchange(pedidosExchange);
                log.info("Exchange '{}' criada com sucesso.", pedidosExchange.getName());
            } catch (Exception e) {
                log.error("Erro ao criar a exchange '{}': {}", pedidosExchange.getName(), e.getMessage());
            }
        };
    }


}
