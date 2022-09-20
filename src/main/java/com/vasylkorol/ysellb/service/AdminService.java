package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepository;
    private final ProductRepository bookRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;
    @Transactional
    public UserDto putUsersStatus(Integer id,boolean isActive) {
        User user = userRepository.findFirstById(id).orElseThrow(()
            -> new UsernameNotFoundException(""));
        user.setActive(isActive);
        userRepository.save(user);
        log.info("a user was saved");
        return userMapper.fromUser(user);
    }
}
