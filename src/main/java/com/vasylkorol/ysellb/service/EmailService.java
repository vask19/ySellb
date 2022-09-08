package com.vasylkorol.ysellb.service;


import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Value(value = "${spring.mail.sender.email}")
    private  String senderEmail;

    public void sendSimpleEmail(EmailReceiver emailReceiver) {
        var message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setSubject(emailReceiver.getSubject());
        message.setText(emailReceiver.getText());
        emailReceiver.getEmails().forEach(email -> {
            message.setTo(email);
            javaMailSender.send(message);
        });
    }

}
