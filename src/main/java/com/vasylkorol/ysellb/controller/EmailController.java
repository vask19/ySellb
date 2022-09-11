package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.service.EmailService;
import com.vasylkorol.ysellb.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/mails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RegistrationService registrationService;


    @PostMapping("/simple")
    public ResponseEntity<?> sentEmailTo(@RequestBody EmailReceiver emailReceiver){
        System.out.println(emailReceiver);
        emailService.sendEmailWithText(emailReceiver);

        return ResponseEntity.ok("ok");

    }

    @GetMapping("/activation/send")
    public ResponseEntity<String> sendActivationCode(Principal principal){
        UserDto userDto = emailService.sendCodeForActivationEmailToUserEmail(principal);
        return ResponseEntity.ok("Code was send on email: " + userDto.getEmail());
    }

    @GetMapping("/activation/check")
    public ResponseEntity<String> activationUsersEmail(@RequestParam("code") Integer code,Principal principal){
        UserDto userDto = emailService.activationUsersEmail(principal,code);
        return ResponseEntity.ok("Your email activated");
    }
}
