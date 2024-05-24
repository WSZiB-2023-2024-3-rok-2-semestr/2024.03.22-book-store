package pl.edu.wszib.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private int id;
    private LocalDateTime date;
    private Status status;
    private User user;
    private Set<Position> positions = new HashSet<>();
    private double sum;


    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return this.date.format(formatter);
    }

    public enum Status {
        NEW,
        PAID,
        SENT,
        DONE
    }
}
