package pl.edu.wszib.book.store.services;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    void save(Book book);
    Optional<Book> getById(Long id);
    void update(Book book, Long id);
    List<Book> getAll();
    List<Book> getByPattern(String pattern);
}
