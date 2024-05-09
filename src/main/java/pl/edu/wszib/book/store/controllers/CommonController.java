package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.services.IBookService;

@Controller
public class CommonController {

    private final IBookService bookService;

    public CommonController(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(required = false) String pattern) {
        if(pattern == null) {
            model.addAttribute("books", this.bookService.getAll());
            model.addAttribute("pattern", "");
        } else {
            model.addAttribute("books", this.bookService.getByPattern(pattern));
            model.addAttribute("pattern", pattern);
        }
        return "index";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }
}
