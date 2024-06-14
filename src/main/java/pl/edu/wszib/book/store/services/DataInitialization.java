package pl.edu.wszib.book.store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.User;

@Service
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {

    private final IUserDAO userDAO;
    private final IBookDAO bookDAO;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("dziala !!!");
        /*this.userDAO.save(new User(0, "Janusz", "Kowalski",
                "janusz", DigestUtils.md5DigestAsHex("janusz123".getBytes()), User.Role.USER));
        this.userDAO.save(new User(0, "Wiesiek", "Admin",
                "wiesiek", DigestUtils.md5DigestAsHex("wiesiek123".getBytes()), User.Role.ADMIN));
        this.userDAO.save(new User(0, "admin", "admin",
                "admin", DigestUtils.md5DigestAsHex("admin".getBytes()), User.Role.ADMIN));*/

        this.bookDAO.save(new Book(null,
                "Java. Podejście funkcyjne. Rozszerzanie obiektowego kodu Javy o zasady programowania funkcyjnego",
                "Ben Weidig", "978-83-289-0651-8", 56.55, 10));
        this.bookDAO.save(new Book(null,
                "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-8", 83.85, 10));
        this.bookDAO.save(new Book(null,
                "Java. Podręcznik na start",
                "Krzysztof Krocz", "978-83-289-1024-9", 44.85, 10));
        this.bookDAO.save(new Book(null,
                "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee",
                "978-83-283-9984-6", 89.40, 10));
    }
}
