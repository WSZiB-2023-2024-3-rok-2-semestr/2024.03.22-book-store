package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.exceptions.LoginAlreadyExistException;
import pl.edu.wszib.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements IUserDAO {
    private final List<User> users = new ArrayList<>();
    private final IdSequence idSequence;

    public UserRepository(IdSequence idSequence) {
        this.idSequence = idSequence;
        this.users.add(new User(this.idSequence.getId(), "Janusz", "Kowalski",
                "janusz", DigestUtils.md5DigestAsHex("janusz123".getBytes()), User.Role.USER));
        this.users.add(new User(this.idSequence.getId(), "Wiesiek", "Admin",
                "wiesiek", DigestUtils.md5DigestAsHex("wiesiek123".getBytes()), User.Role.ADMIN));
    }

    @Override
    public Optional<User> getById(final int id) {
        return this.users.stream()
                .filter(user -> user.getId() == id)
                .findAny()
                .map(this::copy);
    }

    @Override
    public Optional<User> getByLogin(final String login) {
        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .map(this::copy);
    }

    @Override
    public List<User> getAll() {
        return this.users.stream().map(this::copy).toList();
    }

    @Override
    public void save(User user) {
        user.setId(this.idSequence.getId());
        this.getByLogin(user.getLogin()).ifPresent(u -> {
            throw new LoginAlreadyExistException();
        });
        this.users.add(user);
    }

    @Override
    public void remove(final int id) {
        this.users.removeIf(user -> user.getId() == id);
    }

    @Override
    public void update(final User user) {
        this.users.stream()
                .filter(u -> u.getId() == user.getId())
                .findAny()
                .ifPresent(u -> {
            u.setName(user.getName());
            u.setSurname(user.getSurname());
            u.setLogin(user.getLogin());
            u.setPassword(user.getPassword());
            u.setRole(user.getRole());
        });
    }

    private User copy(User user) {
        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        return u;
    }
}
