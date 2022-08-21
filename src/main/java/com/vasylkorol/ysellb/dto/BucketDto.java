package com.vasylkorol.ysellb.dto;


import com.vasylkorol.ysellb.model.Book;
import com.vasylkorol.ysellb.model.User;
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
    private List<Book> books;
}
