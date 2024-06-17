package pl.edu.wszib.book.store.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.BookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.services.IBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    /*private final IBookDAO bookDAO;*/
    private final BookDAO bookDAO;

    @Override
    public void save(Book book) {
        this.bookDAO.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return this.bookDAO.findById(id);
    }

    @Override
    public void update(Book book, Long id) {
        book.setId(id);
        this.bookDAO.save(book);
    }

    @Override
    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        this.bookDAO.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.bookDAO.findByTitleLikeOrAuthorLike(pattern);
    }
}
