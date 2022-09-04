package com.vasylkorol.ysellb.model;
import com.vasylkorol.ysellb.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private int id;

    private String firstName;
    private String secondName;
    private String email;

    private String username;

    private String password;

    private String phoneNumber;

    private boolean active = true;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    private Role role;

}
