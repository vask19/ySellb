package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.service.ProductService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class MainController {
    private final ProductService productService;
    private final UserService userService;



    @GetMapping("")
    public String homePage(Model model){
        List<ProductDto> productDtoList = productService.getAll();
        model.addAttribute("productDtoList",productDtoList);
        return "home";
    }

    @GetMapping("/settings")
    public String getSettingsPage(Principal principal){
        UserDto userDto = userService.getUserByUsername(principal.getName());
        return userDto.getRole() == Role.ROLE_USER ? "user/settings_page" : "admin/settings_page";
    }



}

