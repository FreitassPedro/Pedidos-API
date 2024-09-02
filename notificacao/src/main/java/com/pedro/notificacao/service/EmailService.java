package com.pedro.notificacao.service;

import com.pedro.notificacao.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("pedidos-api@email.com");
        simpleMailMessage.setTo(pedido.getEmailNotificacao());
        simpleMailMessage.setSubject("Pedido " + pedido.getId() + " gerado com sucesso!");
        simpleMailMessage.setText(gerarMensagem(pedido));
        mailSender.send(simpleMailMessage);
    }

    private String gerarMensagem(Pedido pedido) {
        return String.format("Olá, %s! Seu pedido no valor de R$ %d foi gerado com sucesso." +
                        "\n O n° do pedido é %s. " +
                        "\n Status: %s",
                pedido.getCliente(), pedido.getValorTotal(), pedido.getId(), pedido.getStatus());
    }
}
