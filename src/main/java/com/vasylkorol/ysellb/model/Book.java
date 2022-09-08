package com.vasylkorol.ysellb.model;


import com.sun.istack.Interned;
import com.vasylkorol.ysellb.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    private String author;
    private String publishingHouse;
    @Enumerated(EnumType.STRING)
    private Language language;//enum
    private Integer yearOfPublication;//Year
    private String numberOfPages;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
        mappedBy = "book")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }


}
