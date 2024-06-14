package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(Long id);
    List<Book> getAll();
    List<Book> getByPattern(String pattern);
    void save(Book book);
    void update(Book book);
    void remove(Long id);
}
