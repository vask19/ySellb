package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.payload.request.LoginRequest;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.payload.response.JWTTokenSuccessResponse;
import com.vasylkorol.ysellb.payload.response.MessageResponse;
import com.vasylkorol.ysellb.security.JWTUtil;
import com.vasylkorol.ysellb.service.RegistrationService;
import com.vasylkorol.ysellb.util.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private final AuthenticationManager authenticationManager;

    private final UserValidator userValidator;


    @PostMapping("/authentication")
    public ResponseEntity<Object> authentication(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        try {
            authenticationManager.authenticate(token);
            //TODO
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String jwt = jwtUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> performRegistration(@RequestBody @Valid SignupRequest signupRequest,
                                                      BindingResult bindingResult){

        userValidator.validate(signupRequest,bindingResult);

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new MessageResponse("User with such username already exists"),HttpStatus.CONFLICT);
        }


        signupRequest =  registrationService.register(signupRequest);
        String jwt = jwtUtil.generateToken(signupRequest.getUsername());
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));



    }



}
