package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/mails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;


    @PostMapping("/simple")
    public ResponseEntity<?> sentEmailTo(@RequestBody EmailReceiver emailReceiver){
        System.out.println(emailReceiver);
        emailService.sendSimpleEmail(emailReceiver);

        return ResponseEntity.ok("ok");

    }
}
