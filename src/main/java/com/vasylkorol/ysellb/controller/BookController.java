package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import com.vasylkorol.ysellb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @GetMapping("")
    public ResponseEntity<List<BookDto>> getAll(){
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") int id){
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity<BookDto> createBook(@RequestPart("files") MultipartFile[] files,
                                              @RequestPart("book") BookDto bookDto
                                              ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
       return new ResponseEntity<>(bookService.saveNewBook(bookDto,principal,files),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDto> deleteBookFromUser(@PathVariable Integer id,Principal principal){
        return new ResponseEntity<>(bookService.deleteBook(id,principal),HttpStatus.OK);

    }

}
