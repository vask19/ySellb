package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.payload.request.EmailActivationCode;
import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.service.EmailService;
import com.vasylkorol.ysellb.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("api/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final RegistrationService registrationService;

    @GetMapping("/activation/send")
    public String  sendActivationCode(Principal principal, Model model){
        UserDto userDto = emailService.sendCodeForActivationEmailToUserEmail(principal);
        model.addAttribute("code",new EmailActivationCode());
        model.addAttribute("userDto",userDto);
        return "email/email_page";
    }
    @PostMapping("/activation/check")
    public String activationUsersEmail(@ModelAttribute("code") EmailActivationCode code, Principal principal){



        UserDto userDto = emailService.activationUsersEmail(principal,code.toInt());
        return "redirect:/logout";
    }
}
