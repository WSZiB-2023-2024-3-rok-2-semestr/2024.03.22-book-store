package pl.edu.wszib.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void save(Book book) {
        this.bookDAO.save(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public void update(Book book, int id) {
        book.setId(id);
        this.bookDAO.update(book);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.bookDAO.getByPattern(pattern);
    }
}
