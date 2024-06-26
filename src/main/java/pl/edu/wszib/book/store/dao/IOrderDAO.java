package pl.edu.wszib.book.store.dao;

import pl.edu.wszib.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void persist(Order order);
    List<Order> getOrderByUserId(Long userId);
    Optional<Order> getOrderById(Long id);
}
