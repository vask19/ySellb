package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class MainController {
    private final ProductService productService;



    @GetMapping("")
    public String homePage(Model model){
        List<ProductDto> productDtoList = productService.getAll();
        model.addAttribute("productDtoList",productDtoList);
        return "home";
    }



}

