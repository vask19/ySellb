package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.BookDto;
import com.vasylkorol.ysellb.model.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    Book toBook(BookDto bookDto);

    @InheritInverseConfiguration
    BookDto fromBook(Book book);

    List<Book> toBookList(List<BookDto> bookDtoList);

    List<BookDto> fromBookList(List<Book> books);




}
