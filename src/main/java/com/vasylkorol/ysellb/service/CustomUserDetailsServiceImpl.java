package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService{

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new CustomUserDetails(user.get());

    }
}
