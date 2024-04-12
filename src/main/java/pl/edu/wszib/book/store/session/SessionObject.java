package pl.edu.wszib.book.store.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.book.store.model.User;

@Component
@SessionScope
@Getter
@Setter
public class SessionObject {
    User user;
    String info;
    int cos;
}
