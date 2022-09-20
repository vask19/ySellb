package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.ProductDto;
import com.vasylkorol.ysellb.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);

    List<Product> toProductList(List<ProductDto> productDtoList);

    List<ProductDto> fromProductList(List<Product> products);




}
