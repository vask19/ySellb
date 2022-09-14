package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.model.Message;
import com.vasylkorol.ysellb.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/{id}")
    public ResponseEntity<String > sendMessage(@PathVariable("id") Integer id, Principal principal,@RequestBody String messageText){
        Message message = messageService.sendMessage(principal,id,messageText);
        return ResponseEntity.ok(message.getText());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Message>> getAllMessagesWithUserById(@PathVariable("id") Integer recipientId,Principal principal){
        var messages = messageService.getAllMessageWithUserById(principal,recipientId);
        return ResponseEntity.ok(messages);
    }
}
