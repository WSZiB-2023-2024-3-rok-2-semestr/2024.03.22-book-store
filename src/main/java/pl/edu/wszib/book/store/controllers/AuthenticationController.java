package pl.edu.wszib.book.store.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.session.SessionObject;

import java.util.Optional;

@Controller
public class AuthenticationController {

    private final IUserDAO userDAO;

    @Autowired
    HttpSession httpSession;

    /*@Resource
    SessionObject sessionObject;*/

    public AuthenticationController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginInfo", this.httpSession.getAttribute("loginInfo"));
        this.httpSession.removeAttribute("loginInfo");
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login2(@RequestParam String login, @RequestParam String password) {
        Optional<User> user = this.userDAO.getByLogin(login);
        if(user.isPresent() &&
                DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.get().getPassword())) {
            httpSession.setAttribute("user", user.get());
            return "redirect:/";
        }
        this.httpSession.setAttribute("loginInfo", "złe dane");
        return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.httpSession.removeAttribute("user");
        return "redirect:/";
    }
}
