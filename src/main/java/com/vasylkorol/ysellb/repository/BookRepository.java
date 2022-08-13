package com.vasylkorol.ysellb.repository;


import com.vasylkorol.ysellb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


}
