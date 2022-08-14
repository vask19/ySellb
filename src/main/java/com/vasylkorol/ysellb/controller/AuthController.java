package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.payload.response.JWTTokenSuccessResponse;
import com.vasylkorol.ysellb.security.JWTUtil;
import com.vasylkorol.ysellb.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final JWTUtil jwtUtil;
    private final UserMapper userMapper = UserMapper.MAPPER;
    private final RegistrationService registrationService;





    @PostMapping("/registration")
    public ResponseEntity<Object> performRegistration(@RequestBody @Valid UserDto userDto,
                                              BindingResult bindingResult){
        User user = userMapper.toUser(userDto);

        registrationService.register(user);


        String jwt = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));



    }



}
