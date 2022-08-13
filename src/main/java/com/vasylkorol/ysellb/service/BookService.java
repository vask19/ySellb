package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.mapper.BookMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper mapper = BookMapper.MAPPER;


    public List<BookDto> getAll() {
        return mapper.fromBookList(bookRepository.findAll());
    }

    public BookDto saveNewBook(BookDto bookDto,CustomUserDetails principal) {
        Book book = mapper.toBook(bookDto);
        book.setUser(getUserByPrincipal(principal));

        return mapper.fromBook(bookRepository.save(book));
    }


    public User getUserByPrincipal(CustomUserDetails principal){
        return userRepository.findFirstByUsername(principal.getUser().getUsername())
                .orElseThrow(() ->  new UsernameNotFoundException("User not found"));


    }
}
