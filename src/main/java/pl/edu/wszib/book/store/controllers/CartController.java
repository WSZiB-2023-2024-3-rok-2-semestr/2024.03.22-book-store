package pl.edu.wszib.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.services.ICartService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    private final IBookDAO bookDAO;

    private final ICartService cartService;

    @Autowired
    HttpSession httpSession;

    public CartController(IBookDAO bookDAO, ICartService cartService) {
        this.bookDAO = bookDAO;
        this.cartService = cartService;
    }

    @GetMapping(path = "/add/{id}")
    public String addToCart(@PathVariable final Long id) {
        this.cartService.addBookToCart(id);
        return "redirect:/";
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("cartSum", this.cartService.calculateCartSum());
        return "cart";
    }

    /*@GetMapping(path = "/test")
    public String test(Model model) {
        return "test.css";
    }*/
}
