package com.vasylkorol.ysellb;

import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.UserService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserMapper userMapper= Mappers.getMapper(UserMapper.class);
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void saveUser(){

    }
}
