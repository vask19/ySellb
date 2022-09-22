package com.vasylkorol.ysellb.service;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.mapper.ProductMapper;
import com.vasylkorol.ysellb.model.Bucket;
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
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ImageRepository imageRepository;

    public List<ProductDto> getAll() {

        return mapper.fromProductList(productRepository.findAll());
    }

    public ProductDto saveBook(ProductDto bookDto){
        return mapper.fromProduct(productRepository.save(mapper.toProduct(bookDto)));
    }

    @Transactional
    public ProductDto saveNewProduct(ProductDto productDto, CustomUserDetails principal, MultipartFile[] multipartFiles) {
        Product product = mapper.toProduct(productDto);
        List<Image> images = Arrays.stream(multipartFiles)
                .filter(el -> el.getSize() != 0)
                .map(this::toImageEntity)
                .toList();
        images.forEach(image -> {
            image.setProduct(product);
            product.getImages().add(image);});
        images.get(0).setPreviewImage(true);
        product.setUser(getUserByPrincipal(principal));
        log.info("a new product with photos was saved");
        Product bookFromDb = productRepository.save(product);
        bookFromDb.setPreviewImageId(bookFromDb.getImages().get(0).getId());
        productRepository.save(product);
        log.info( "a new product with preview photo was saved");
        return mapper.fromProduct(productRepository.save(product));
    }

    public Image toImageEntity(MultipartFile file){
        try {
            return Image.builder()
                    .name(file.getName())
                    .originalFileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .size(file.getSize())
                    .bytes(file.getBytes())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return new Image();
        }
    }


    public User getUserByPrincipal(CustomUserDetails principal){
        return userRepository.findFirstByUsername(principal.getUser().getUsername())
                .orElseThrow(() ->  new UsernameNotFoundException("User not found"));
    }

    public ProductDto getProduct(int id) {
        return mapper.fromProduct(productRepository.findById(id).orElse(new Product()));

    }
    @Transactional
    public ProductDto deleteProduct(Integer id, Principal principal) {
        Product product = productRepository.findById(id).orElseThrow(()
            -> new UsernameNotFoundException("product not found"));
        if (product.getUser().getUsername().equals(principal.getName())) {
            product.getBuckets()
                    .forEach(bucket -> bucket.getProducts().remove(product));
            ProductDto productDto = mapper.fromProduct(product);
            productRepository.delete(product);
            log.info("a product was deleted");
            return productDto;
        }
        else return new ProductDto();
    }
}
