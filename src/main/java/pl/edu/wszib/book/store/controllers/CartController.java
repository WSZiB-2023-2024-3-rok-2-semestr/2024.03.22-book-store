package pl.edu.wszib.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Position;
import pl.edu.wszib.book.store.session.SessionConstants;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    private final IBookDAO bookDAO;

    @Autowired
    HttpSession httpSession;

    public CartController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping(path = "/add/{id}")
    public String addToCart(@PathVariable final int id) {
        Optional<Book> bookBox = this.bookDAO.getById(id);

        bookBox.ifPresent(book -> {
            final Set<Position> cart = (Set<Position>) this.httpSession.getAttribute(SessionConstants.CART_KEY);
            Optional<Position> alreadyBookPosition = cart.stream()
                    .filter(p -> p.getBook().getId() == id)
                    .findFirst();

            alreadyBookPosition.ifPresentOrElse(Position::incrementQuantity,
                    () -> cart.add(new Position(0, book, 1)));
        });

        return "redirect:/";
    }

    @GetMapping
    public String cart(Model model) {
        return "cart";
    }

    /*@GetMapping(path = "/test")
    public String test(Model model) {
        return "test.css";
    }*/
}
