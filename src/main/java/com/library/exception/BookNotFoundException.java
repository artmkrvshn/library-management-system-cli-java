package com.library.exception;

public class BookNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Book with this ID [%d] doesn't exist";

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Long id) {
        super(String.format(DEFAULT_MESSAGE, id));
    }

    public BookNotFoundException(Long id, Throwable cause) {
        super(String.format(DEFAULT_MESSAGE, id), cause);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }
}