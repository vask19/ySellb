package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.mapper.SignupMapper;
import com.vasylkorol.ysellb.model.Avatar;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.model.enums.Role;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import com.vasylkorol.ysellb.repository.AvatarRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignupMapper signupMapper = SignupMapper.MAPPER;
    private final AvatarRepository avatarRepository;

    @Transactional
    public SignupRequest register(SignupRequest signupRequest, MultipartFile avatar) {
        User user = signupMapper.toUser(signupRequest);
        Avatar imageAvatar = toImageEntity(avatar);
        avatarRepository.save(imageAvatar);
        imageAvatar.setUser(user);
        user.setAvatar(imageAvatar);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_NOT_CONFIRMED_USER);
        userRepository.save(user);
        log.info("user has been saved");
        log.info( "new user has been registered");
        return signupMapper.fromUser(user);
    }

    public Avatar toImageEntity(MultipartFile file){
        try {

            return Avatar.builder()
                    .name(file.getName())
                    .originalFileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .bytes(file.getBytes())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return new Avatar();
        }
    }

}

