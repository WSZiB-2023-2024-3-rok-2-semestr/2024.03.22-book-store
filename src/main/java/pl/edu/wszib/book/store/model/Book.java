package pl.edu.wszib.book.store.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "tbook")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Column(length = 20/*, columnDefinition = "TEXT"*/)
    private String isbn;
    private double price;
    private int quantity;
    /*@Transient
    private String jakasZmienna;*/

    public Book(Long id) {
        this.id = id;
    }
}
