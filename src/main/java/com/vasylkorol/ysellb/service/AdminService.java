package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;



    public List<UserDto> getAllUsers(){
        return userMapper.fromUserList(userRepository.findAll());
    }

}
