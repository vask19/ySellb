package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.BucketRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BucketService {
    private final BucketRepository bucketRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository bookRepository;
    private final ProductMapper bookMapper = ProductMapper.MAPPER;

    @Transactional
    public Bucket createBucket(User user) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        bucket.setProducts(new ArrayList<>());
        log.info( "Created bucket to user");
        return bucketRepository.save(bucket);

    }
    public User getUserByPrincipal(Principal principal) {
        return userRepository.findFirstByUsername(principal.getName()).orElseThrow(()
                -> new UsernameNotFoundException("User not exists"));
    }

    private List<Product> getCollectRefBooksByIds(List<Integer> bookIds) {
        return bookIds.stream()
                .map(bookRepository::getOne)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDto addProduct(Integer bookId, Principal principal) {
        User user =  getUserByPrincipal(principal);

        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        if (bucket == null){
            bucket = createBucket(user);}
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefBooksByIds(Collections.singletonList(bookId)));
<<<<<<< HEAD
        bucket.setBooks(newProductList);
        log.info( "Added book to bucket");
        bucketRepository.save(bucket);
        log.info("bucket saved");
        return bookMapper.fromBook(bookRepository.getReferenceById(bookId));
=======
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
        log.info( "Added product to bucket");
        return bookMapper.fromProduct(bookRepository.getReferenceById(bookId));
>>>>>>> create-chat

    }

    public BucketDto getBucketByUser(Principal principal){
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);

        if (bucket == null) {
            bucket = createBucket(user);
        }
        List<Product> books = bucket.getProducts();
        List<Integer> bookIds = books.stream().map(Product::getId).toList();
        BucketDto bucketDto = new BucketDto();
        bucketDto.setProducts(bookMapper.fromProductList(getCollectRefBooksByIds(bookIds)));
        return bucketDto;

    }
    @Transactional
    public ProductDto deleteBook(Integer id, Principal principal) {
        User user =  getUserByPrincipal(principal);
        Bucket bucket = bucketRepository.findByUser(user).orElse(null);
        Product book = bookRepository.findById(id).orElseThrow(()
            -> new UsernameNotFoundException(""));
<<<<<<< HEAD
        bucket.getBooks().remove(book);
        log.info( "a book from bucket was deleted ");
=======
        bucket.getProducts().remove(book);
>>>>>>> create-chat
        bucketRepository.save(bucket);
        log.info("a bucket was saved");

        return bookMapper.fromProduct(book);

    }
}

