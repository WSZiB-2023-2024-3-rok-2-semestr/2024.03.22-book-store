package pl.edu.wszib.book.store.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Long id) {
        this.id = id;
    }

    public enum Role {
        ADMIN,
        USER
    }
}
