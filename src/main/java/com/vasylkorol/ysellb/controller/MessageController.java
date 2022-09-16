package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.ChatDto;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final ChatService chatService;

    @PostMapping("/{id}")
    public ResponseEntity<MessageDto> sendMessage(@PathVariable("id") Integer id, Principal principal,@RequestBody String messageText){

        MessageDto messageDto = chatService.sendMessage(principal,id,messageText);
        return ResponseEntity.ok(messageDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ChatDto> getAllMessagesWithUserById(@PathVariable("id") Integer recipientId, Principal principal){
        var chatDto = chatService.getChat(principal,recipientId);
        return ResponseEntity.ok(chatDto);
    }
}
