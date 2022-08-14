package com.vasylkorol.ysellb.model;
import com.vasylkorol.ysellb.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String username;

    private String password;

    private boolean isActive = true;

    @OneToMany(mappedBy = "user")
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    private Role role;









}
