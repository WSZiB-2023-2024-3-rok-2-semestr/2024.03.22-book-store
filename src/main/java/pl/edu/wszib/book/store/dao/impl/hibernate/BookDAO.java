package pl.edu.wszib.book.store.dao.impl.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.book.store.dao.IBookDAO;
import pl.edu.wszib.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    private final SessionFactory sessionFactory;

    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Book> getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query =
                session.createQuery("FROM pl.edu.wszib.book.store.model.Book WHERE id = :id", Book.class);
        query.setParameter("id", id);

        try {
            Book book = query.getSingleResult();
            return Optional.of(book);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query =
                session.createQuery("FROM pl.edu.wszib.book.store.model.Book", Book.class);
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query =
                session.createQuery("FROM pl.edu.wszib.book.store.model.Book WHERE title LIKE :pattern OR author LIKE :pattern", Book.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public void save(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Long id) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.remove(new Book(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
