package com.vasylkorol.ysellb;
import com.vasylkorol.ysellb.mapper.BookMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class )
@SpringBootTest
class BookServiceTest {

    public static final  Integer BOOK_ID = 1;
    @MockBean
    private  BookRepository bookRepository;
    @MockBean
    private  UserRepository userRepository;

    private  BookMapper mapper = BookMapper.MAPPER;
    @MockBean
    private  ImageRepository imageRepository;

    @InjectMocks
    private BookService bookService;



    @Test
    public void getAll(){

    }

    @Test
    public void getBook(){
        Optional<Book> book = Optional.of(new Book(1));
        when(bookRepository.findById(1)).thenReturn(Optional.of(new Book(1)));
        assertEquals(book,bookRepository.findById(1));







    }
}
