package com.vasylkorol.ysellb.dto;
import com.vasylkorol.ysellb.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/dto/BookDto.java

=======
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/dto/ProductDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private int id;

    private String image;

    private String description;

    private String name;

    private String author;

    private String publishingHouse;

    private Language language;

    private int yearOfPublication;

    private String numberOfPages;


    public ProductDto(Integer bookId) {
        this.id = bookId;
    }
}
