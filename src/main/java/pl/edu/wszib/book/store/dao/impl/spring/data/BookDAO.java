package pl.edu.wszib.book.store.dao.impl.spring.data;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.book.store.model.Book;

import java.util.List;

public interface BookDAO extends CrudRepository<Book, Long> {
    List<Book> findByTitleLikeOrAuthorLike(String pattern1, String pattern2);

    default List<Book> findByTitleLikeOrAuthorLike(String pattern) {
        return findByTitleLikeOrAuthorLike(pattern, pattern);
    }
}
