package com.vasylkorol.ysellb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class MainController {


    @GetMapping("")
    public String homePage(){
        return "home";
    }

    @GetMapping("/log")
    public String h(){

        return "redirect:/logout";
    }
}

