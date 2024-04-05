package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.impl.memory.BookRepository;

@Controller
public class CommonController {

    private final IBookDAO bookDAO;

    public CommonController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(required = false) String pattern) {
        if(pattern == null) {
            model.addAttribute("books", this.bookDAO.getAll());
            model.addAttribute("pattern", "");
        } else {
            model.addAttribute("books", this.bookDAO.getByPattern(pattern));
            model.addAttribute("pattern", pattern);
        }
        model.addAttribute("logged", AuthenticationController.logged);
        return "index";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }
}
