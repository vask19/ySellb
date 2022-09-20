package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import com.vasylkorol.ysellb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") int id){
        return ResponseEntity.ok(productService.getBook(id));
    }


    @ResponseBody
    @PostMapping("")
    public ResponseEntity<ProductDto> createProduct(@RequestPart("files") MultipartFile[] files,
                                                 @RequestPart("product") ProductDto productDto
                                              ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();

       return new ResponseEntity<>(productService.saveNewBook(productDto,principal,files),HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductDto> deleteProductFromUser(@PathVariable Integer id, Principal principal){
        return new ResponseEntity<>(productService.deleteBook(id,principal),HttpStatus.OK);

    }

}
