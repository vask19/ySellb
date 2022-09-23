package com.vasylkorol.ysellb.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vasylkorol.ysellb.model.enums.Language;
import lombok.*;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
        mappedBy = "product")
    @ToString.Exclude
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    private boolean deleted;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Bucket> buckets;

    public Product(Integer productId) {
        this.id = productId;
    }

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
