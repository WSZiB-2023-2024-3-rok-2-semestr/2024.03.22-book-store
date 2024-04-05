package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.model.User;

import java.util.Optional;

@Controller
public class AuthenticationController {

    private final IUserDAO userDAO;

    public static boolean logged = false;

    public AuthenticationController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login2(@RequestParam String login, @RequestParam String password) {
        Optional<User> user = this.userDAO.getByLogin(login);
        if(user.isPresent() &&
                DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.get().getPassword())) {
            logged = true;
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
