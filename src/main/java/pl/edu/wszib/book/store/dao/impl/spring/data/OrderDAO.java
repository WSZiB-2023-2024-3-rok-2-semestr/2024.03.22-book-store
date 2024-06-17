package pl.edu.wszib.book.store.dao.impl.spring.data;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.book.store.model.Order;

public interface OrderDAO extends CrudRepository<Order, Long> {
}
