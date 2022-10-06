package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.ChatDto;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/{id}")
    public String  sendMessage(@PathVariable("id") Integer id, Principal principal,@ModelAttribute("messageDto") MessageDto messageDto){
        MessageDto message = chatService.sendMessage(principal,id,messageDto.getText());
        return "home";
    }

    @GetMapping("")
    public String getAllChats(Principal principal, Model model){
        List<ChatDto> chatDtoList =
                chatService.getAllChatDtoList(principal);
        model.addAttribute("chatDtoList",chatDtoList);
        return "chat/chats_page";
    }


    @GetMapping("{id}")
    public String getAllMessagesWithUserById(@PathVariable("id") Integer recipientId, Principal principal){
        var chatDto = chatService.getChat(principal,recipientId);
        return "chat/chats_page";
    }
}
