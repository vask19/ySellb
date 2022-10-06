package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    public Image findById(Long id){
        return imageRepository.findById(id).orElse(new Image());
    }
}
