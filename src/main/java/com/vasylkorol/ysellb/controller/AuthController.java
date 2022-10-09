package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.service.RegistrationService;
import com.vasylkorol.ysellb.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/auth/")
public class AuthController {
    private final RegistrationService registrationService;

    private final UserValidator userValidator;


    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("signupRequest") SignupRequest signupRequest){
        return "auth/registration";

    }



    @PostMapping("/registration")
    public String registration(@ModelAttribute("signupRequest") @Valid SignupRequest signupRequest,
                               BindingResult bindingResult,
                               @RequestPart("avatar")MultipartFile avatar){
        userValidator.validate(signupRequest,bindingResult);

        if (bindingResult.hasErrors()){
            return "auth/registration";
        }
        registrationService.register(signupRequest,avatar);
        return "redirect:/login";

    }
}
