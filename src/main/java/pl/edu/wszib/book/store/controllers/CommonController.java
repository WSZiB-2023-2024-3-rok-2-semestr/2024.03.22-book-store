package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.impl.memory.BookRepository;

@Controller
public class CommonController {

    private final IBookDAO bookRepository;

    public CommonController(IBookDAO bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        bookRepository.getBooks();
        return "index";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }
}
