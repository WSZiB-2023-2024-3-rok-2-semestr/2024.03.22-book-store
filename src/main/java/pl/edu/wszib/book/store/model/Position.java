package pl.edu.wszib.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Position {
    private int id;
    private Book book;
    private int quantity;

    public void incrementQuantity() {
        this.quantity++;
    }
}
