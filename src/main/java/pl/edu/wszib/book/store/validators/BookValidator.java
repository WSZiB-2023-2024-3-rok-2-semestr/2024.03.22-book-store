package pl.edu.wszib.book.store.validators;

import pl.edu.wszib.book.store.exceptions.BookValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookValidator {

    public static void validateTitle(String title) {
        String regex = "^[A-Z].*$";
        if(!title.matches(regex)) {
            throw new BookValidationException();
        }
    }

    public static void validateAuthor(String author) {
        String regex = "^[A-Z][a-z]{2,} [A-Z][a-z]+$";
        if(!author.matches(regex)) {
            throw new BookValidationException();
        }
    }

    public static void validateIsbn(String isbn) {
        String regex = "^(978|979)-83-[0-9]{2,6}-[0-9]{2,6}-[0-9]{1}$";
        if(!isbn.matches(regex)) {
            throw new BookValidationException();
        }
    }
}
