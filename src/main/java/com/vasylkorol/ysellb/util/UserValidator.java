package com.vasylkorol.ysellb.util;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.service.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {


    private final CustomUserDetailsServiceImpl userDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //TODO : create equals service
        UserDto user = (UserDto) target;
        try {
            userDetailsService.loadUserByUsername(user.getUsername());

        }catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("username","","User with such username already exists");





    }
}
