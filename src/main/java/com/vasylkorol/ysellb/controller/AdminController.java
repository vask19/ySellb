package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.service.AdminService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;
    private final UserService userService;



    @PutMapping("users/{id}/block")
    public ResponseEntity<UserDto> blockUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.putUserStatus(id,false),HttpStatus.OK);
    }

    @PutMapping("users/{id}/unblock")
    public ResponseEntity<UserDto> unBlockUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.putUserStatus(id,true),HttpStatus.OK);
    }





    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
}
