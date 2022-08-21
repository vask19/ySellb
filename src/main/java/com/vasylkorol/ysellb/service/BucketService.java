package com.vasylkorol.ysellb.service;

import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.mapper.BucketMapper;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BucketService {


    private final BucketRepository bucketRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BucketMapper bucketMapper = BucketMapper.MAPPER;



    @Transactional
    public Bucket createBucket(User user){
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucket.setBooks(new ArrayList<>());
        return bucketRepository.save(bucket);

    }



    public User getUserByPrincipal(Principal principal){
        return  userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

    @Transactional
    public BucketDto getBucket(Principal principal){
        User user = userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
        -> new UsernameNotFoundException("User not exists"));
        Bucket bucket = user.getBucket();
        if (bucket == null){
            bucket = createBucket(user);
            user.setBucket(bucket);
            userService.saveUser(user);
        }

        BucketDto bucketDto = new BucketDto();
        bucketDto.setBooks(bucket.getBooks());
        bucketDto.setId(user.getId());
        bucketDto.setId(bucket.getId());



        return bucketDto;





    }


    @Transactional
    public BookDto addBook(int id, Principal principal) {
       User user = getUserByPrincipal(principal);
       Bucket bucket = user.getBucket();
       Book book = bookRepository.findById(id).orElseThrow(()
       -> new UsernameNotFoundException(""));
       bucket.getBooks().add(book);
       bucketRepository.save(bucket);

        return new BookDto();



    }
}
