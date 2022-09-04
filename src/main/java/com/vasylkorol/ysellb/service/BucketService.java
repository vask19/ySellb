package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.mapper.BookMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.BucketRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BucketService {


    private final BucketRepository bucketRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.MAPPER;


    @Transactional
    public Bucket createBucket(User user) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucket.setBooks(new ArrayList<>());
        return bucketRepository.save(bucket);

    }


    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

    private List<Book> getCollectRefBooksByIds(List<Integer> bookIds) {
        return bookIds.stream()
                .map(bookRepository::getOne)
                .collect(Collectors.toList());
    }




    @Transactional
    public BookDto addBook(Integer bookId, Principal principal) {
        User user =  getUserByPrincipal(principal);

        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        if (bucket == null){
            bucket = createBucket(user);}
        List<Book> products = bucket.getBooks();
        List<Book> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefBooksByIds(Collections.singletonList(bookId)));
        bucket.setBooks(newProductList);
        bucketRepository.save(bucket);

        return bookMapper.fromBook(bookRepository.getReferenceById(bookId));

    }

    public BucketDto getBucketByUser(Principal principal){
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);

        if (bucket == null) {
            bucket = createBucket(user);
        }
        List<Book> books = bucket.getBooks();
        List<Integer> bookIds = books.stream().map(Book::getId).toList();
        BucketDto bucketDto = new BucketDto();
        bucketDto.setBooks(bookMapper.fromBookList(getCollectRefBooksByIds(bookIds)));
        return bucketDto;

    }


    @Transactional
    public BookDto deleteBook(Integer id, Principal principal) {
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        Book book = bookRepository.findById(id).orElseThrow(()
            -> new UsernameNotFoundException(""));
        bucket.getBooks().remove(book);
        bucketRepository.save(bucket);
        return bookMapper.fromBook(book);

    }
}







