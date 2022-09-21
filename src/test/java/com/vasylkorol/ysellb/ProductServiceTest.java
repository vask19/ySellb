package com.vasylkorol.ysellb;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.model.Image;
import com.vasylkorol.ysellb.model.Product;
import com.vasylkorol.ysellb.repository.ProductRepository;
import com.vasylkorol.ysellb.repository.ImageRepository;
import com.vasylkorol.ysellb.repository.UserRepository;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import com.vasylkorol.ysellb.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class ProductServiceTest {

    public static final  Integer BOOK_ID = 1;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private  UserRepository userRepository;

    private ProductMapper mapper = ProductMapper.MAPPER;
    @Mock
    private  ImageRepository imageRepository;

    @InjectMocks
    private ProductService productService;







    @Test
    public void addBook(){
        assertEquals(0,0);
        ProductDto productDto = new ProductDto();

        CustomUserDetails customUserDetails = Mockito.mock(CustomUserDetails.class);
        MultipartFile[] files = {Mockito.mock(MultipartFile.class)};
        Image image = productService.toImageEntity(files[0]);
        System.out.println(image);
        var r = productService.saveNewProduct(productDto,customUserDetails,files);





//        doReturn(new Image())
//                 .when(productService).toImageEntity();
        assertEquals(0,0);

    }

    /*
  @Transactional
    public ProductDto saveNewBook(ProductDto bookDto, CustomUserDetails principal, MultipartFile[] multipartFiles) {
        Product book = mapper.toProduct(bookDto);
        List<Image> images = Arrays.stream(multipartFiles)
                .filter(el -> el.getSize() != 0)
                .map(this::toImageEntity)
                .toList();
        images.forEach(image -> {
            image.setProduct(book);
            book.getImages().add(image);});
        images.get(0).setPreviewImage(true);
        book.setUser(getUserByPrincipal(principal));
        log.info("a new book with photos was saved");
        Product bookFromDb = productRepository.save(book);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        productRepository.save(book);
        log.info( "a new book with preview photo was saved");
        return mapper.fromProduct(productRepository.save(book));
    }
    * */



    @Test
    public void getProduct(){
        doReturn(Optional.of(new Product(1)))
                 .when(productRepository).findById(1);
        var actualResult = productService.getProduct(1);
        var exceptedResult = new ProductDto(1);
        assertEquals(exceptedResult,actualResult);
        verify(productRepository,Mockito.times(1)).findById(1);
    }
}
