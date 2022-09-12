package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.mapper.UserMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;
    @Transactional
    public UserDto putUsersStatus(Integer id,boolean isActive) {
        User user = userRepository.findFirstById(id).orElseThrow(()
            -> new UsernameNotFoundException(""));
        user.setActive(isActive);
        userRepository.save(user);
        return userMapper.fromUser(user);
    }
}
