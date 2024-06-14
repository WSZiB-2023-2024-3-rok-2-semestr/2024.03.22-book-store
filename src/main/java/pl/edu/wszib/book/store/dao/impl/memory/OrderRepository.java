package pl.edu.wszib.book.store.dao.impl.memory;

import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository implements IOrderDAO {

    private final List<Order> orders = new ArrayList<>();

    private final IdSequence idSequence;

    public OrderRepository(IdSequence idSequence) {
        this.idSequence = idSequence;
    }

    @Override
    public void persist(Order order) {
        order.setId((long) this.idSequence.getId());
        this.orders.add(order);
    }

    @Override
    public List<Order> getOrderByUserId(final Long userId) {
        return this.orders.stream().filter(o -> o.getUser().getId().equals(userId)).toList();
    }

    @Override
    public Optional<Order> getOrderById(final Long id) {
        return this.orders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }
}
