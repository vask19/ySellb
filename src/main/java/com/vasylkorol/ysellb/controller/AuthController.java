package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/auth/")
public class AuthController {

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user")User user){
        return "auth/registration";

    }
}
