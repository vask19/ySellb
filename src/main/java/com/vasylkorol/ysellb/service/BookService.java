package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.mapper.BookMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.repository.BookRepository;
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
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper mapper = BookMapper.MAPPER;
    private final ImageRepository imageRepository;

    public List<BookDto> getAll() {

        return mapper.fromBookList(bookRepository.findAll());
    }

    public BookDto saveBook(BookDto bookDto){
        return mapper.fromBook(bookRepository.save(mapper.toBook(bookDto)));
    }

    @Transactional
    public BookDto saveNewBook(BookDto bookDto, CustomUserDetails principal, MultipartFile[] multipartFiles) {
        Book book = mapper.toBook(bookDto);
        List<Image> images = Arrays.stream(multipartFiles)
                .filter(el -> el.getSize() != 0)
                .map(this::toImageEntity)
                .toList();
        images.forEach(image -> {
            image.setBook(book);
            book.getImages().add(image);});
        images.get(0).setPreviewImage(true);
        book.setUser(getUserByPrincipal(principal));
        Book bookFromDb = bookRepository.save(book);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        bookRepository.save(book);
        log.info( "Saved a new book");
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

    public BookDto getBook(int id) {
        log.info("Gat a book");
        return mapper.fromBook(bookRepository.findById(id).orElse(new Book()));
    }

    //TODO
    @Transactional
    public BookDto deleteBook(Integer id, Principal principal) {
        Book book = bookRepository.findById(id).orElseThrow(()
            -> new UsernameNotFoundException("11"));
        if (book.getUser().getUsername().equals(principal.getName())) {
            BookDto bookDto = mapper.fromBook(book);
            bookRepository.delete(book);
            return bookDto;
        }
        else return new BookDto();
    }
}
