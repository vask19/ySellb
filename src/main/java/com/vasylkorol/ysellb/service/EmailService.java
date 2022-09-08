package com.vasylkorol.ysellb.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Value(value = "${spring.mail.sender.email}")
    private  String senderEmail;
    @Value(value = "${spring.mail.sender.text}")
    private  String senderText;


    public void sendSimpleEmail(String receiver) {
        var message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(receiver);
        message.setSubject("Txt");
        message.setText("Text");
        javaMailSender.send(message);
    }

}
