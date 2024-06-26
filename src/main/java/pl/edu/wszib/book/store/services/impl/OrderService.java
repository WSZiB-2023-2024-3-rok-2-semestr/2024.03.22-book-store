package pl.edu.wszib.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.BookDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.OrderDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.UserDAO;
import pl.edu.wszib.book.store.exceptions.EmptyCartException;
import pl.edu.wszib.book.store.exceptions.IncorrectCartPositionsException;
import pl.edu.wszib.book.store.exceptions.UserNotLoggedException;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Order;
import pl.edu.wszib.book.store.model.Position;
import pl.edu.wszib.book.store.model.User;
import pl.edu.wszib.book.store.services.IOrderService;
import pl.edu.wszib.book.store.session.SessionConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final HttpSession httpSession;
    /*private final IBookDAO bookDAO;
    private final IOrderDAO orderDAO;*/
    private final BookDAO bookDAO;
    private final OrderDAO orderDAO;
    private final UserDAO userDAO;

    @Override
    public void confirmOrder() {
        Set<Position> cart = (Set<Position>) this.httpSession.getAttribute(SessionConstants.CART_KEY);
        if(cart == null || cart.isEmpty()) {
            throw new EmptyCartException();
        }
        List<Position> toRemove = cart.stream().filter(p -> {
            Optional<Book> bookBox = this.bookDAO.findById(p.getBook().getId());
            return bookBox.isEmpty() || bookBox.get().getQuantity() < p.getQuantity();
        }).toList();

        if(!toRemove.isEmpty()) {
            toRemove.forEach(cart::remove);
            throw new IncorrectCartPositionsException();
        }

        Order order = new Order();
        order.setDate(LocalDateTime.now());
        order.setUser((User) this.httpSession.getAttribute(SessionConstants.USER_KEY));
        order.setStatus(Order.Status.NEW);
        order.getPositions().addAll(cart);
        order.setSum(order.getPositions().stream()
                .mapToDouble(p -> p.getQuantity() * p.getBook().getPrice()).sum());

        order.getPositions().forEach(p -> {
            this.bookDAO.findById(p.getBook().getId()).ifPresent(
                    book -> {
                        book.setQuantity(book.getQuantity() - p.getQuantity());
                        this.bookDAO.save(book);
                    }
            );
        });

        this.orderDAO.save(order);
        cart.clear();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        User user = (User) this.httpSession.getAttribute(SessionConstants.USER_KEY);
        if(user == null) {
            throw new UserNotLoggedException();
        }

        /*return this.orderDAO.getOrderByUserId(user.getId());*/
        return new ArrayList<>(this.userDAO.findById(user.getId()).get().getOrders());
    }
}
