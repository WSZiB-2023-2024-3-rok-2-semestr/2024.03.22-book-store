package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getById(Long id);
    Optional<User> getByLogin(String login);
    List<User> getAll();
    void save(User user);
    void remove(Long id);
    void update(User user);
}
