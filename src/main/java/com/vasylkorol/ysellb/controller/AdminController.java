package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.service.AdminService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;
    private final UserService userService;



    @PutMapping("users/{id}/block")
    public ResponseEntity<UserDto> blockUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.putUsersStatus(id,false),HttpStatus.OK);
    }

    @PutMapping("users/{id}/unblock")
    public ResponseEntity<UserDto> unBlockUser(@PathVariable("id") Integer id){
        return new ResponseEntity<>(adminService.putUsersStatus(id,true),HttpStatus.OK);
    }





    @GetMapping("/users")
    public String  getAllUsers(Model model){
        var userDtoList = userService.getAllUsers();
        model.addAttribute("userDtoList",userDtoList);
        return "admin/all_users_page";

    }

    @GetMapping("users/{id}")
    public String  getUser(@PathVariable("id") Integer id,Model model){
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("userDto",userDto);
        return "admin/user_info";

    }

    @GetMapping("")
    public String getAdminPanelPage(){
        return "admin/admin_panel_page";
    }
}
