package pl.edu.wszib.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pl.edu.wszib.book.store.dao.IUserDAO;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.services.IAuthenticationService;
import pl.edu.wszib.book.store.session.SessionConstants;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final IUserDAO userDAO;
    private final HttpSession httpSession;

    public AuthenticationService(IUserDAO userDAO, HttpSession httpSession) {
        this.userDAO = userDAO;
        this.httpSession = httpSession;
    }

    @Override
    public void login(String login, String password) {
        Optional<User> user = this.userDAO.getByLogin(login);
        if(user.isPresent() &&
                DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.get().getPassword())) {
            httpSession.setAttribute(SessionConstants.USER_KEY, user.get());
            httpSession.setAttribute(SessionConstants.CART_KEY, new HashSet<>());
            return;
        }
        this.httpSession.setAttribute("loginInfo", "złe dane");
    }

    @Override
    public void logout() {
        this.httpSession.removeAttribute(SessionConstants.USER_KEY);
        this.httpSession.removeAttribute(SessionConstants.CART_KEY);
    }

    @Override
    public String getLoginInfo() {
        String temp = (String) this.httpSession.getAttribute("loginInfo");
        this.httpSession.removeAttribute("loginInfo");
        return temp;
    }
}
