package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.ChatDto;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.mapper.ChatMapper;
import com.vasylkorol.ysellb.mapper.MessageMapper;
import com.vasylkorol.ysellb.model.Chat;
import com.vasylkorol.ysellb.model.Message;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.ChatRepository;
import com.vasylkorol.ysellb.repository.MessageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper = MessageMapper.MAPPER;
    private final ChatMapper chatMapper = ChatMapper.MAPPER;

    @Transactional
    public MessageDto sendMessage(Principal principal, Integer recipientId,String messageText){
        User sender = getUserByPrincipal(principal);
        User recipient = userRepository.findFirstById(recipientId).orElseThrow(
                () -> new UsernameNotFoundException("Recipient not found")
        );
        log.info("User {} tried to send message to user {}", sender.getUsername(),recipient.getUsername());

        Chat chat = chatRepository.findByRecipientAndSenderOrSenderAndRecipient(sender,recipient)
                .orElseGet(() ->
        {
            return chatRepository.save(Chat.builder()
                    .sender(sender)
                    .messages(new ArrayList<>())
                    .recipient(recipient).build());
        });
        Message message = Message.builder()
                .text(messageText)
                .dateOfCreate(LocalDateTime.now())
                .chat(chat)
                .build();
        messageRepository.save(message);
        log.info("a message was  saved");
        chat.getMessages().add(message);
        chatRepository.save(chat);
        log.info("a chat was  saved");
        log.info("User {} sent message to uses {}", sender.getUsername(),recipient.getUsername());
        return messageMapper.fromMessage(message);

    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

    public ChatDto getChat(Principal principal, Integer recipientId) {
       User sender = getUserByPrincipal(principal);
       User recipient = userRepository.findFirstById(recipientId).orElseThrow(
               () -> new UsernameNotFoundException("Recipient not fount ")
       );
        Chat chat = chatRepository.findByRecipientAndSenderOrSenderAndRecipient(sender,recipient)
                .orElseThrow(() -> new UsernameNotFoundException("Chat not found"));
        return chatMapper.fromChat(chat);

    }
}