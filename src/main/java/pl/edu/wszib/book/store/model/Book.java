package pl.edu.wszib.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;
}
