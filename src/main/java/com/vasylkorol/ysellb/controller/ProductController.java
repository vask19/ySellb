package com.vasylkorol.ysellb.controller;
import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.security.CustomUserDetails;
import com.vasylkorol.ysellb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.vasylkorol.ysellb.model.enums.Type;



import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("")
    public String allProductsPages(Model model){
        List<ProductDto> productDtoList = productService.getAll();
        model.addAttribute("productDtoList",productDtoList);
        return "product/product_page";
    }

    @GetMapping("/add")
    public String addProductPage(Model model){
        model.addAttribute("productDto",new ProductDto());
        return "product/add_product_page";
    }

    @GetMapping("/{id}")
    public String  getProduct(@PathVariable("id") int id,Model model){
        ProductDto productDto =  productService.getProduct(id);
        model.addAttribute("productDto",productDto);
        System.out.println("product----" + productDto);
        return "product/product_info";
    }



    @PostMapping("")
    public String  addProduct(@RequestPart("file1") MultipartFile file1,
                              @RequestPart("file2") MultipartFile file2,
                              @RequestPart("file3") MultipartFile file3,
                                                 @ModelAttribute("productDto") ProductDto productDto
                                              ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        productService.saveNewProduct(productDto,principal,new MultipartFile[]{file1,file2,file3});
        return "redirect:/api/products";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProductFromUser(@PathVariable Integer id, Principal principal){
        return new ResponseEntity<>(productService.deleteProduct(id,principal),HttpStatus.OK);

    }

}
