package com.vasylkorol.ysellb;
import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.mapper.BookMapper;
import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.repository.BookRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class BookServiceTest {

    public static final  Integer BOOK_ID = 1;
    @Mock
    private  BookRepository bookRepository;
    @Mock
    private  UserRepository userRepository;

    private  BookMapper mapper = BookMapper.MAPPER;
    @Mock
    private  ImageRepository imageRepository;

    @InjectMocks
    private BookService bookService;





    @Test
    public void getBook(){
        doReturn(Optional.of(new Book(1)))
                 .when(bookRepository).findById(1);
        var actualResult = bookService.getBook(1);
        var exceptedResult = new BookDto(1);
        assertEquals(exceptedResult,actualResult);
        verify(bookRepository,Mockito.times(1)).findById(1);
    }
}
