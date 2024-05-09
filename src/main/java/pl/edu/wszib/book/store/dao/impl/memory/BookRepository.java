package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements IBookDAO {
    List<Book> books = new ArrayList<>();
    private final IdSequence idSequence;

    public BookRepository(IdSequence idSequence) {
        this.idSequence = idSequence;
        books.add(new Book(this.idSequence.getId(),
                "Java. Podejście funkcyjne. Rozszerzanie obiektowego kodu Javy o zasady programowania funkcyjnego",
                "Ben Weidig", "978-83-289-0651-8", 56.55, 10));
        books.add(new Book(this.idSequence.getId(),
                "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-8", 83.85, 10));
        books.add(new Book(this.idSequence.getId(),
                "Java. Podręcznik na start",
                "Krzysztof Krocz", "978-83-289-1024-9", 44.85, 10));
        books.add(new Book(this.idSequence.getId(),
                "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee",
                "978-83-283-9984-6", 89.40, 10));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream()
                .filter(book -> book.getId() == id)
                .findAny()
                .map(this::copy);
    }

    @Override
    public List<Book> getAll() {
        return this.books.stream().map(this::copy).toList();
    }

    @Override
    public List<Book> getByPattern(final String pattern) {
        return this.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(pattern.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(pattern.toLowerCase()))
                .map(this::copy)
                .toList();
    }

    @Override
    public void save(Book book) {
        book.setId(this.idSequence.getId());
        this.books.add(book);
    }

    @Override
    public void update(final Book book) {
        this.books.stream()
                .filter(b -> b.getId() == book.getId())
                .findAny()
                .ifPresent(b -> {
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setIsbn(book.getIsbn());
            b.setPrice(book.getPrice());
            b.setQuantity(book.getQuantity());
        });
    }

    @Override
    public void remove(int id) {
        this.books.removeIf(book -> book.getId() == id);
    }

    private Book copy(Book book) {
        Book copy = new Book();
        copy.setId(book.getId());
        copy.setTitle(book.getTitle());
        copy.setAuthor(book.getAuthor());
        copy.setIsbn(book.getIsbn());
        copy.setPrice(book.getPrice());
        copy.setQuantity(book.getQuantity());
        return copy;
    }
}
