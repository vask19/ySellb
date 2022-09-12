package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.SignupMapper;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.payload.request.EmailReceiver;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignupMapper signupMapper = SignupMapper.MAPPER;

    @Transactional
    public SignupRequest register(SignupRequest signupRequest) {
        User user = signupMapper.toUser(signupRequest);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_NOT_CONFIRMED_USER);
        userRepository.save(user);
        log.info( "Registered a new user");
        return signupMapper.fromUser(user);
    }

}

