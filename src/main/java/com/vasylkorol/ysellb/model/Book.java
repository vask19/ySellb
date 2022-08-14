package com.vasylkorol.ysellb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String author;
    private String publishingHouse;
    private String language;
    private String  yearOfPublication;
    private String numberOfPages;
    //TODO
    private String image;


    @ManyToOne
    @JoinColumn(name = "users_id",referencedColumnName = "id")
    private User user;

}