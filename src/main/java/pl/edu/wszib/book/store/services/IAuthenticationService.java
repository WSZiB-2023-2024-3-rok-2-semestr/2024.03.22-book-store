package pl.edu.wszib.book.store.services;

public interface IAuthenticationService {
    void login(String login, String password);
    void logout();
    String getLoginInfo();
}
