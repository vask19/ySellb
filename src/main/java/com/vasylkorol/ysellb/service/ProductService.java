package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository bookRepository;
    private final UserRepository userRepository;
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ImageRepository imageRepository;

    public List<ProductDto> getAll() {

        return mapper.fromBookList(bookRepository.findAll());
    }

    public ProductDto saveBook(ProductDto bookDto){
        return mapper.fromBook(bookRepository.save(mapper.toBook(bookDto)));
    }

    @Transactional
    public ProductDto saveNewBook(ProductDto bookDto, CustomUserDetails principal, MultipartFile[] multipartFiles) {
        Product book = mapper.toBook(bookDto);
        List<Image> images = Arrays.stream(multipartFiles)
                .filter(el -> el.getSize() != 0)
                .map(this::toImageEntity)
                .toList();
        images.forEach(image -> {
            image.setBook(book);
            book.getImages().add(image);});
        images.get(0).setPreviewImage(true);
        book.setUser(getUserByPrincipal(principal));
<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/service/BookService.java
        Book bookFromDb = bookRepository.save(book);
        log.info("a new book with photos was saved");
=======
        Product bookFromDb = bookRepository.save(book);
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/service/ProductService.java
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        bookRepository.save(book);
        log.info( "a new book with preview photo was saved");
        return mapper.fromBook(bookRepository.save(book));
    }

    private Image toImageEntity(MultipartFile file){
        try {
            return Image.builder()
                    .name(file.getName())
                    .originalFileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .bytes(file.getBytes())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return new Image();
        }
    }


    public User getUserByPrincipal(CustomUserDetails principal){
        return userRepository.findFirstByUsername(principal.getUser().getUsername())
                .orElseThrow(() ->  new UsernameNotFoundException("User not found"));
    }

<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/service/BookService.java
    public BookDto getBook(int id) {
        return mapper.fromBook(bookRepository.findById(id).orElse(new Book()));
=======
    public ProductDto getBook(int id) {
        log.info("Gat a book");
        return mapper.fromBook(bookRepository.findById(id).orElse(new Product()));
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/service/ProductService.java
    }

    @Transactional
    public ProductDto deleteBook(Integer id, Principal principal) {
        Product book = bookRepository.findById(id).orElseThrow(()
            -> new UsernameNotFoundException("11"));
        if (book.getUser().getUsername().equals(principal.getName())) {
<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/service/BookService.java
            Book deletedBook = new Book(book.getId());
            deletedBook.setDeleted(true);
            bookRepository.save(deletedBook);
            BookDto bookDto = mapper.fromBook(deletedBook);
            log.info("a book was deleted");
=======
            ProductDto bookDto = mapper.fromBook(book);
            bookRepository.delete(book);
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/service/ProductService.java
            return bookDto;
        }
        else return new ProductDto();
    }
}
