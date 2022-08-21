package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.service.BookService;
import com.vasylkorol.ysellb.service.BucketService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ResponseEntity<BucketDto> getBucket(Principal principal){

        BucketDto bucketDto = bucketService.getBucket(principal);
        return ResponseEntity.ok(bucketDto


        );
    }

}
