package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.service.BookService;
import com.vasylkorol.ysellb.service.BucketService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bucket/")
public class BucketController {
    private final UserService userService;
    private final BookService bookService;
    private final BucketService bucketService;



    @GetMapping("/add/{id}")
    public BookDto addBookToBucket(@PathVariable("id") int id,Principal principal){
        return bucketService.addBook(id,principal);


    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getBucket(Principal principal){
        BucketDto bucketDto = bucketService.getBucketByUser(principal);
        bucketService.getBucketByUser(principal);
        return new ResponseEntity<>((bucketDto.getBooks() == null
                ? Collections.emptyList()
                : bucketDto.getBooks()),HttpStatus.OK);



    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Integer id,Principal principal){
        return new ResponseEntity<>(bucketService.deleteBook(id,principal), HttpStatus.OK);
    }

}
