package com.vasylkorol.ysellb.dto;


import com.vasylkorol.ysellb.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

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

    private Language language;

    private int yearOfPublication;

    private String numberOfPages;


}
