package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.Message;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.MessageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    @Transactional
    public Message sendMessage(Principal principal, Integer recipientId,String messageText){
        User sender = getUserByPrincipal(principal);
        User recipient = userRepository.findFirstById(recipientId).orElseThrow(
                () -> new UsernameNotFoundException("Recipient not found")
        );
        Message message = Message.builder()
                .recipient(recipient)
                .sender(sender)
                .text(messageText)
                .build();
        return messageRepository.save(message);

    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

    public List<Message> getAllMessageWithUserById(Principal principal, Integer recipientId) {
       User sender = getUserByPrincipal(principal);
       User recipient = userRepository.findFirstById(recipientId).orElseThrow(
               () -> new UsernameNotFoundException("Recipient not fount ")
       );
        var messages = messageRepository.findByRecipientAndSender(sender,recipient);
        messages.addAll(messageRepository.findByRecipientAndSender(recipient,sender));
       return  messages;
    }
}
