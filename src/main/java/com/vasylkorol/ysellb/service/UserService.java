package com.vasylkorol.ysellb.service;


import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.UpdateUserRequest;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper= Mappers.getMapper(UserMapper.class);
    private final BookRepository bookRepository;


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






    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findFirstByUsername(userDto.getUsername()).orElseThrow(()->
                new UsernameNotFoundException("User not found"));
        User newUser = userMapper.toUser(userDto);
        newUser.setPassword(user.getPassword());
        newUser.setBooks(user.getBooks() == null ? new ArrayList<>() : user.getBooks());
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
        return userDto;
    }
}
