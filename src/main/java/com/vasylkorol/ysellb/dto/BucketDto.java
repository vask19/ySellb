package com.vasylkorol.ysellb.dto;
<<<<<<< HEAD
=======


>>>>>>> create-chat
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BucketDto {

    private int id;
    private List<ProductDto> products;
}
