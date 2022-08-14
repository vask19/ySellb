package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);


        return null;
    }
}
