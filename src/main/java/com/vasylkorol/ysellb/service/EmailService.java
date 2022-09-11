package com.vasylkorol.ysellb.service;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final UserMapper userMapper = UserMapper.MAPPER;
    private final UserRepository userRepository;
    @Value(value = "${spring.mail.sender.email}")
    private  String senderEmail;


    public void sendEmailWithText(EmailReceiver emailReceiver) {
        var message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setSubject(emailReceiver.getSubject());
        message.setText(emailReceiver.getText());
        emailReceiver.getEmails().forEach(email -> {
            message.setTo(email);
            javaMailSender.send(message);
        });
    }

    @Transactional
    public UserDto sendCodeForActivationEmailToUserEmail(Principal principal){
        User user = getUserByPrincipal(principal);
        int code = (new Random().nextInt(100000,900000));
        user.setEmailActivationCode(code);
        String text = "Your registration code: " + code;
        EmailReceiver emailReceiver = new EmailReceiver();
        emailReceiver.setEmails(Collections.singletonList(user.getEmail()));
        emailReceiver.setSubject("Registration on ySellb");
        emailReceiver.setText(text);
        sendEmailWithText(emailReceiver);
        userRepository.save(user);
        return userMapper.fromUser(user);
    }
    @Transactional
    public UserDto activationUsersEmail(Principal principal,Integer userAuthCode){
        User user = getUserByPrincipal(principal);
        if (user.getEmailActivationCode() == userAuthCode){
            user.setActiveEmail(true);
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }
        return userMapper.fromUser(user);


    }
    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

}
