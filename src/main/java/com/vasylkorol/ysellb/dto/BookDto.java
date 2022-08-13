package com.vasylkorol.ysellb.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private String image;

    private String description;

    private String name;

    private String author;

    private String publishingHouse;

    private String language;

    private String  yearOfPublication;

    private String numberOfPages;


}
