package com.vasylkorol.ysellb.controller;

import com.vasylkorol.ysellb.model.Avatar;
import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.AvatarService;
import com.vasylkorol.ysellb.service.ImageService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping("/api/avatars/")
@RequiredArgsConstructor
public class AvatarController {
    private final UserRepository userRepository;

    private final AvatarService avatarService;
    @GetMapping("/{username}")
    public ResponseEntity<?> getAvatarById(@PathVariable("username") String  username) {
        User user = userRepository.findFirstByUsername(username).get();
        Avatar avatar = avatarService.findById(user.getAvatar().getId());
        return ResponseEntity.ok()
                .header("fileName",avatar.getOriginalFileName())
                .contentType(MediaType.valueOf(avatar.getContentType()))
                .contentLength(avatar.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(avatar.getBytes())));


    }
}
