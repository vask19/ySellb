package com.vasylkorol.ysellb.repository;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface BucketRepository extends JpaRepository<Bucket,Integer> {
    Optional<Bucket> findByUser(User user);
}
