package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper= Mappers.getMapper(UserMapper.class);
    private final ProductRepository productRepository;
    public UserDto getUserByUsername(String username){
        return userMapper.fromUser(userRepository.findFirstByUsername(username).orElse(new User()));
    }



    @Transactional
    public List<UserDto> getAllUsers(){
        return userMapper.fromUserList(userRepository.findAll());
    }

    @Transactional
    public UserDto getUserById(Integer id) {
        User user = userRepository.findFirstById(id).orElseThrow(()->
                new UsernameNotFoundException("User not found with id: " + id));

        return userMapper.fromUser(user);
    }
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findFirstByUsername(userDto.getUsername()).orElseThrow(()->
                new UsernameNotFoundException("User not found with username: " + userDto.getUsername()));
        User newUser = userMapper.toUser(userDto);
        newUser.setPassword(user.getPassword());
        newUser.setProducts(user.getProducts() == null ? new ArrayList<>() : user.getProducts());
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
        log.info( "user was updated");
        return userDto;
    }
}
