package pl.edu.wszib.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.dao.impl.spring.data.BookDAO;
import pl.edu.wszib.book.store.model.Book;
import pl.edu.wszib.book.store.model.Position;
import pl.edu.wszib.book.store.services.ICartService;
import pl.edu.wszib.book.store.session.SessionConstants;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    /*private final IBookDAO bookDAO;*/
    private final BookDAO bookDAO;
    private final HttpSession httpSession;


    public double calculateCartSum() {
        Set<Position> cart = (Set<Position>) this.httpSession.getAttribute(SessionConstants.CART_KEY);
        if(cart == null) {
            return 0.0;
        }
        return cart.stream().mapToDouble(p -> p.getQuantity() * p.getBook().getPrice()).sum();
    }

    @Override
    public void addBookToCart(Long id) {
        Optional<Book> bookBox = this.bookDAO.findById(id);

        bookBox.ifPresent(book -> {
            final Set<Position> cart = (Set<Position>) this.httpSession.getAttribute(SessionConstants.CART_KEY);
            Optional<Position> alreadyBookPosition = cart.stream()
                    .filter(p -> p.getBook().getId().equals(id))
                    .findFirst();

            alreadyBookPosition.ifPresentOrElse(Position::incrementQuantity,
                    () -> cart.add(new Position(null, book, 1)));
        });
    }
}
