package com.vasylkorol.ysellb.repository;

import com.vasylkorol.ysellb.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
}
