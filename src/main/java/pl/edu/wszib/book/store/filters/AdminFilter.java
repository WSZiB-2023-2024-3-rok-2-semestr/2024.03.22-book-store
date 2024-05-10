package pl.edu.wszib.book.store.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.session.SessionConstants;

import java.io.IOException;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession httpSession = req.getSession();

        if(httpSession == null ||
                !(httpSession.getAttribute(SessionConstants.USER_KEY) instanceof User u) ||
                u.getRole() != User.Role.ADMIN) {
            res.sendRedirect("/");
        }

        chain.doFilter(request, response);
    }
}
