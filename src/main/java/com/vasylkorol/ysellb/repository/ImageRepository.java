package com.vasylkorol.ysellb.repository;

import com.vasylkorol.ysellb.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
