package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.service.ProductService;
import com.vasylkorol.ysellb.service.BucketService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bucket/")
public class BucketController {
    private final UserService userService;
    private final ProductService productService;
    private final BucketService bucketService;



    @GetMapping("/add/{id}")
    public ProductDto addProductToBucket(@PathVariable("id") int id, Principal principal){
        return bucketService.addProduct(id,principal);


    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getBucket(Principal principal){
        BucketDto bucketDto = bucketService.getBucketByUser(principal);
        bucketService.getBucketByUser(principal);
        return new ResponseEntity<>((bucketDto.getBooks() == null
                ? Collections.emptyList()
                : bucketDto.getBooks()),HttpStatus.OK);



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteBook(@PathVariable Integer id, Principal principal){
        return new ResponseEntity<>(bucketService.deleteBook(id,principal), HttpStatus.OK);
    }

}
