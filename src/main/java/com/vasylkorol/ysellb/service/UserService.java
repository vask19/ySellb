package com.vasylkorol.ysellb.service;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper= Mappers.getMapper(UserMapper.class);


    public UserDto getUserByUsername(String username){
        return userMapper.fromUser(userRepository.findFirstByUsername(username).orElse(new User()));
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers(){
        return userMapper.fromUserList(userRepository.findAll());
    }

    public UserDto getUserById(Integer id) {
        return userMapper.fromUser(userRepository.findFirstById(id).orElse(new User()));
    }
}
