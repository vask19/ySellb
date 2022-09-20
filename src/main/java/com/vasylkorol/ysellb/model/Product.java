package com.vasylkorol.ysellb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vasylkorol.ysellb.model.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/model/Book.java
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
=======
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/model/Product.java

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private User user;
<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/model/Book.java
    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,
        mappedBy = "book")
=======
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
        mappedBy = "product")
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/model/Product.java
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    private boolean deleted;

<<<<<<< HEAD:src/main/java/com/vasylkorol/ysellb/model/Book.java
    @ManyToMany(mappedBy = "books",cascade = {CascadeType.REFRESH})
    private List<Bucket> buckets;
    public Book(Integer bookId) {
        this.id = bookId;
=======
    public Product(Integer productId) {
        this.id = productId;
>>>>>>> create-chat:src/main/java/com/vasylkorol/ysellb/model/Product.java
    }

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
