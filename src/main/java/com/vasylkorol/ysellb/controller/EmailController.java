package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;


    @PostMapping("/simple")
    public ResponseEntity<?> to(@RequestParam("to") String to){
        emailService.sendSimpleEmail(to);

        return ResponseEntity.ok("ok");

    }
}
