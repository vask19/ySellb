package com.vasylkorol.ysellb.repository;

import com.vasylkorol.ysellb.model.Message;
import com.vasylkorol.ysellb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByRecipientAndSender(User recipient, User sender);



}