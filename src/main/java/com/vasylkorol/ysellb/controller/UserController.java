package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.UpdateUserRequest;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PutMapping("/update")
    private ResponseEntity<UserDto> updateUser(UserDto userDto){
        return new ResponseEntity<>(userService.updateUser(userDto),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);

    }
}
