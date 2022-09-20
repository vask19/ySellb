package com.vasylkorol.ysellb.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bucket_id")
    private int id;
    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
<<<<<<< HEAD
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "buckets_books",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
=======
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "buckets_products",
    joinColumns = @JoinColumn(name = "bucket_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
>>>>>>> create-chat
}
