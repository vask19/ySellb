package com.vasylkorol.ysellb;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProductServiceTest {

    public static final  Integer BOOK_ID = 1;
    @Mock
    private ProductRepository bookRepository;
    @Mock
    private  UserRepository userRepository;

    private ProductMapper mapper = ProductMapper.MAPPER;
    @Mock
    private  ImageRepository imageRepository;

    @InjectMocks
    private ProductService productService;





    @Test
    public void getBook(){
        doReturn(Optional.of(new Product(1)))
                 .when(bookRepository).findById(1);
        var actualResult = productService.getBook(1);
        var exceptedResult = new ProductDto(1);
        assertEquals(exceptedResult,actualResult);
        verify(bookRepository,Mockito.times(1)).findById(1);
    }
}
