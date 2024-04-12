package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.exceptions.BookValidationException;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.validators.BookValidator;

@Controller
public class BookController {

    private final IBookDAO bookDAO;

    public BookController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping(path = "/book/add", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("bookModel", new Book());
        return "bookForm";
    }

    @PostMapping(path = "/book/add")
    public String addForm2(@ModelAttribute Book book) {
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
}
