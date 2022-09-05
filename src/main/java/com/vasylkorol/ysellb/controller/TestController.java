package com.vasylkorol.ysellb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/test")
public class TestController {




    @ResponseBody
    @PostMapping
    public ResponseEntity<Object> test( @RequestPart("files") MultipartFile[] files
            , @RequestPart("test")Test test){
        return ResponseEntity.ok("");
    }
}
