package pl.edu.wszib.book.store.dao.impl.spring.data;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.book.store.model.User;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {
    /*@Query(value = "select u from pl.edu.wszib.book.store.model.User u where u.login = :login")
    Optional<User> findByLogin(@Param("login") String login);*/

    /*@Query(value = "select * from tuser where login = :login", nativeQuery = true)
    Optional<User> findByLogin(@Param("login") String login);*/

    Optional<User> findByLogin(String login);
}
