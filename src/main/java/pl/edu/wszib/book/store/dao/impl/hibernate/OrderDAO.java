package pl.edu.wszib.book.store.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IOrderDAO;
import pl.edu.wszib.book.store.model.Order;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAO implements IOrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(Order order) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrderByUserId(Long userId) {
        return Collections.emptyList();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query =
                session.createQuery("FROM pl.edu.wszib.book.store.model.Order WHERE id = :id", Order.class);
        query.setParameter("id", id);
        try {
            Order order = query.getSingleResult();
            return Optional.of(order);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
