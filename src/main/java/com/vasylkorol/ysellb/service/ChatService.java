package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.ChatDto;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.exception.ChatNotFoundException;
import com.vasylkorol.ysellb.exception.ProductNotFoundException;
import com.vasylkorol.ysellb.mapper.ChatMapper;
import com.vasylkorol.ysellb.mapper.MessageMapper;
import com.vasylkorol.ysellb.model.Chat;
import com.vasylkorol.ysellb.model.Message;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.repository.ChatRepository;
import com.vasylkorol.ysellb.repository.MessageRepository;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper = MessageMapper.MAPPER;
    private final ChatMapper chatMapper = ChatMapper.MAPPER;
    private final EmailService emailService;
    private final ProductRepository productRepository;

    @Transactional
    public MessageDto sendMessage(Principal principal, Integer recipientId, String messageText, Integer productId){
        User sender = getUserByPrincipal(principal);
        User recipient = userRepository.findFirstById(recipientId).orElseThrow(()
                        -> new UsernameNotFoundException("User not found with username: " + principal.getName())
    );

        log.info("User {} tried to send message to user {}", sender.getUsername(),recipient.getUsername());
        Product product = productRepository.findById(productId).orElseThrow(()
            -> new ProductNotFoundException("Product not found"));

        Chat chat = createChat(sender,recipient,product.getPreviewImageId());
        Message message = Message.builder()
                .text(messageText)
                .sent(true)
                .dateOfCreate(LocalDateTime.now())
                .chat(chat)
                .build();
        messageRepository.save(message);
        chat.getMessages().add(message);
        chatRepository.save(chat);
        log.info("User {} sent message to uses {}", sender.getUsername(),recipient.getUsername());
        EmailReceiver emailReceiver = EmailReceiver.builder()
                .emails(Collections.singletonList(sender.getEmail()))
                .subject("You have a new message")
                .text("User " + sender.getUsername() + " send you a new message!")
                .build();
        emailService.sendEmailWithText(emailReceiver);
        log.info("message was sent to email");
        return messageMapper.fromMessage(message);
    }


    @Transactional
    public Chat createChat(User sender,User recipient,Long chatImageId){
       return chatRepository.findByRecipientAndSenderOrSenderAndRecipient(sender,recipient)
                .orElseGet(() ->
                {
                    return chatRepository.save(Chat.builder()
                            .sender(sender)
                            .recipient(recipient)
                            .chatImageId(chatImageId)
                            .messages(new ArrayList<>())
                            .build());
                });
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not found with username: " + principal.getName()));
    }

    @Transactional
    public ChatDto getChat(Principal principal, Integer recipientId) {
       User sender = getUserByPrincipal(principal);
       User recipient = userRepository.findFirstById(recipientId).orElseThrow(
               () -> new UsernameNotFoundException("User not found with username: " + principal.getName())
       );
        Chat chat = chatRepository.findByRecipientAndSenderOrSenderAndRecipient(sender,recipient)
                .orElseThrow(ChatNotFoundException::new);
        return chatMapper.fromChat(chat);

    }

    @Transactional
    public List<ChatDto> getAllChatDtoList(Principal principal){
        User user = getUserByPrincipal(principal);
        return chatMapper.fromChatList(chatRepository.findAllByRecipientOrSender(user,user)
                .orElseThrow(() -> new ChatNotFoundException("User haven't any chats")));
    }
}
