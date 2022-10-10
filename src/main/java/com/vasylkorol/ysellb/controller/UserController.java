package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.MessageDto;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.service.ProductService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private ProductMapper mapper = ProductMapper.MAPPER;
    @PutMapping("/update")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userDto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String  getUsersPage(@PathVariable("id") Integer id, Model model, @RequestParam(value = "productId") Integer productId){
        UserDto userDto = userService.getUserById(id);
        List<ProductDto> productDtoList = productService.getAllByUserId(id);
        userDto.setProductDtoList(productDtoList);
        model.addAttribute("userDto",userDto);
        model.addAttribute("messageDto",new MessageDto());
        model.addAttribute("productId",productId);
        return "user/user_page";

    }
}
