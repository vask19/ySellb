package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PutMapping("/update")
    private ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        System.out.println(11);
        return new ResponseEntity<>(userService.updateUser(userDto),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String  getUser(@PathVariable("id") Integer id, Model model){
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto",userDto);
        return "user/user_page";

    }
}
