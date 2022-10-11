package com.vasylkorol.ysellb.controller;


import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.service.ProductService;
import com.vasylkorol.ysellb.service.BucketService;
import com.vasylkorol.ysellb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/buckets/")
public class BucketController {
    private final UserService userService;
    private final ProductService productService;
    private final BucketService bucketService;



    @GetMapping("/add/{id}")
    public String  addProductToBucket(@PathVariable("id") int id, Principal principal){
        bucketService.addProduct(id,principal);

        return "redirect:/api/products/" + id;


    }

    @GetMapping()
    public String  getBucket(Principal principal,Model model){
        BucketDto bucketDto = bucketService.getBucketByUser(principal);
        List<ProductDto> productDtoList =  (bucketDto.getProducts() == null
                ? Collections.emptyList()
                : bucketDto.getProducts());
        model.addAttribute("productDtoList",productDtoList);
        return "bucket/bucket_page";




    }

    @DeleteMapping("/{id}")
    public String  deleteProduct(@PathVariable Integer id, Principal principal){
        bucketService.deleteProduct(id,principal);
        return "redirect:/api/buckets/";
    }

}
