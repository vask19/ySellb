package com.vasylkorol.ysellb.repository;
import com.vasylkorol.ysellb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findFirstById(int id);

    Optional<User> findFirstByUsername(String username);
}
