package com.kodilla.library.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(final String message) {
        super(message);
    }
}
