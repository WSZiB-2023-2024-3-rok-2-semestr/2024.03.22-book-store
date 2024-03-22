package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Component;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository implements IBookDAO {
    List<Book> books = new ArrayList<>();

    private int lastId = 0;

    public BookRepository() {
        books.add(new Book(++lastId,
                "Java. Podejście funkcyjne. Rozszerzanie obiektowego kodu Javy o zasady programowania funkcyjnego",
                "Ben Weidig", "978-83-289-0651-8", 56.55, 10));
        books.add(new Book(++lastId,
                "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-8", 83.85, 10));
        books.add(new Book(++lastId,
                "Java. Podręcznik na start",
                "Krzysztof Krocz", "978-83-289-1024-9", 44.85, 10));
        books.add(new Book(++lastId,
                "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee",
                "978-83-283-9984-6", 89.40, 10));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(book -> book.getId() == id).findAny();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public List<Book> getByPattern(final String pattern) {
        return this.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(pattern.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(pattern.toLowerCase()))
                .toList();
    }

    @Override
    public void save(Book book) {
        book.setId(++this.lastId);
        this.books.add(book);
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void remove(int id) {
        this.books.removeIf(book -> book.getId() == id);

        /*Iterator<Book> iterator = this.books.iterator();
        while(iterator.hasNext()) {
            Book book = iterator.next();
            if(book.getId() == id) {
                iterator.remove();
            }
        }*/
    }
}
