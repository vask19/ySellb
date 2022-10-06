package com.vasylkorol.ysellb.repository;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findById(Integer id);
    void deleteById(Integer id);

    List<Product> findAllByUser(User user);


}
