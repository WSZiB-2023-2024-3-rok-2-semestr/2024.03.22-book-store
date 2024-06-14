package pl.edu.wszib.book.store.services;

public interface ICartService {
    double calculateCartSum();
    void addBookToCart(Long id);
}
