package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.model.Avatar;
import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.repository.AvatarRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public Avatar findById(Long id) {
        return avatarRepository.findById(id).orElse(new Avatar());
    }

}
