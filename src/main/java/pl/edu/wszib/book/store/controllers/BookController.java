package pl.edu.wszib.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.exceptions.BookValidationException;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.validators.BookValidator;

import java.util.Optional;

@Controller
public class BookController {

    private final IBookDAO bookDAO;

    private final HttpSession httpSession;

    public BookController(IBookDAO bookDAO, HttpSession httpSession) {
        this.bookDAO = bookDAO;
        this.httpSession = httpSession;
    }

    @RequestMapping(path = "/book/add", method = RequestMethod.GET)
    public String addForm(Model model) {
        if(((User) this.httpSession.getAttribute("user")).getRole() != User.Role.ADMIN) {
            return "redirect:/";
        }
        model.addAttribute("bookModel", new Book());
        return "bookForm";
    }

    @PostMapping(path = "/book/add")
    public String addForm2(@ModelAttribute Book book) {
        if(((User) this.httpSession.getAttribute("user")).getRole() != User.Role.ADMIN) {
            return "redirect:/";
        }
        try {
            BookValidator.validateTitle(book.getTitle());
            BookValidator.validateAuthor(book.getAuthor());
            BookValidator.validateIsbn(book.getIsbn());
        } catch (BookValidationException e) {
            //e.printStackTrace();
            return "redirect:/book/add";
        }
        this.bookDAO.save(book);
        return "redirect:/";
    }

    @RequestMapping(path = "/book/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if(((User) this.httpSession.getAttribute("user")).getRole() != User.Role.ADMIN) {
            return "redirect:/";
        }
        Optional<Book> bookBox = this.bookDAO.getById(id);
        if(bookBox.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("bookModel", bookBox.get());
        }
        return "bookForm";
    }

    @PostMapping(path = "/book/edit/{id}")
    public String edit(@ModelAttribute Book book, @PathVariable int id) {
        if(((User) this.httpSession.getAttribute("user")).getRole() != User.Role.ADMIN) {
            return "redirect:/";
        }
        try {
            BookValidator.validateTitle(book.getTitle());
            BookValidator.validateAuthor(book.getAuthor());
            BookValidator.validateIsbn(book.getIsbn());
        } catch (BookValidationException e) {
            return "redirect:/book/edit/"+id;
        }
        book.setId(id);
        this.bookDAO.update(book);
        return "redirect:/";
    }
}
