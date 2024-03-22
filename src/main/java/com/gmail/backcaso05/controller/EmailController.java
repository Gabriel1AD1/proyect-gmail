package com.gmail.backcaso05.controller;

import com.gmail.backcaso05.dto.Correo;
import com.gmail.backcaso05.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService service;


    @PostMapping("/enviar-correo")
    public String enviarCorreo(@RequestBody Correo correo) {
        imprimir(correo);
        return service.SendEmail(correo);
    }

    public void imprimir(Correo function){
        System.out.println(function);
    }
}