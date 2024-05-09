package pl.edu.wszib.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.book.store.exceptions.BookValidationException;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.services.IBookService;
import pl.edu.wszib.book.store.validators.BookValidator;

import java.util.Optional;

@Controller
public class BookController {

    private final IBookService bookService;

    private final HttpSession httpSession;

    public BookController(IBookService bookService, HttpSession httpSession) {
        this.bookService = bookService;
        this.httpSession = httpSession;
    }

    @RequestMapping(path = "/book/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("bookModel", new Book());
        return "bookForm";
    }

    @PostMapping(path = "/book/add")
    public String add(@ModelAttribute Book book) {
        try {
            BookValidator.validateTitle(book.getTitle());
            BookValidator.validateAuthor(book.getAuthor());
            BookValidator.validateIsbn(book.getIsbn());
        } catch (BookValidationException e) {
            //e.printStackTrace();
            return "redirect:/book/add";
        }
        this.bookService.save(book);
        return "redirect:/";
    }

    @RequestMapping(path = "/book/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Optional<Book> bookBox = this.bookService.getById(id);
        if(bookBox.isEmpty()) {
            return "redirect:/";
        } else {
            model.addAttribute("bookModel", bookBox.get());
        }
        return "bookForm";
    }

    @PostMapping(path = "/book/edit/{id}")
    public String edit(@ModelAttribute Book book, @PathVariable int id) {
        try {
            BookValidator.validateTitle(book.getTitle());
            BookValidator.validateAuthor(book.getAuthor());
            BookValidator.validateIsbn(book.getIsbn());
        } catch (BookValidationException e) {
            return "redirect:/book/edit/"+id;
        }
        this.bookService.update(book, id);
        return "redirect:/";
    }
}
