package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    Optional<User> getById(int id);
    Optional<User> getByLogin(String login);
    List<User> getAll();
    void save(User user);
    void remove(int id);
    void update(User user);
}
