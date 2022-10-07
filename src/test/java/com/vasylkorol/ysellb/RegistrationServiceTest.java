package com.vasylkorol.ysellb;

import com.vasylkorol.ysellb.mapper.SignupMapper;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class RegistrationServiceTest {

    @Mock
    private  UserRepository userRepository;
    private  SignupMapper signupMapper = SignupMapper.MAPPER;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @InjectMocks
    private RegistrationService registrationService;


    @Test
    public void registerUserTest(){
        SignupRequest signupRequest = SignupRequest.builder()
                .username("test")
                .build();
        User user = User.builder()
                .active(true)
                .role(Role.ROLE_NOT_CONFIRMED_USER)
                .firstName("test")
                .username("test")
                .phoneNumber("test")
                .email("test")
                .build();
        doReturn(user)
                .when(userRepository).save(user);
        var actualResult = registrationService.register(signupRequest);
        assertEquals(actualResult,signupRequest);
        verify(userRepository).save(user);




    }
}
