package pl.edu.wszib.book.store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.BookDAO;
import pl.edu.wszib.book.store.model.Book;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final BookDAO bookDAO;

    @GetMapping("/test1")
    public void test() {
        System.out.println("dziala !!!");
        /*this.userDAO.save(new User(0, "Janusz", "Kowalski",
                "janusz", DigestUtils.md5DigestAsHex("janusz123".getBytes()), User.Role.USER));
        this.userDAO.save(new User(0, "Wiesiek", "Admin",
                "wiesiek", DigestUtils.md5DigestAsHex("wiesiek123".getBytes()), User.Role.ADMIN));
        this.userDAO.save(new User(0, "admin", "admin",
                "admin", DigestUtils.md5DigestAsHex("admin".getBytes()), User.Role.ADMIN));*/

        this.bookDAO.save(new Book(0L,
                "Java. Podejście funkcyjne. Rozszerzanie obiektowego kodu Javy o zasady programowania funkcyjnego",
                "Ben Weidig", "978-83-289-0651-8", 56.55, 10));
        this.bookDAO.save(new Book(0L,
                "Java. Przewodnik dla początkujących. Wydanie IX",
                "Herbert Schildt", "978-83-289-0479-8", 83.85, 10));
        this.bookDAO.save(new Book(0L,
                "Java. Podręcznik na start",
                "Krzysztof Krocz", "978-83-289-1024-9", 44.85, 10));
        this.bookDAO.save(new Book(0L,
                "Java. Rusz głową! Wydanie III", "Kathy Sierra, Bert Bates, Trisha Gee",
                "978-83-283-9984-6", 89.40, 10));
    }
}
