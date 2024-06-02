package com.library.exception;

public class ReaderNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_MESSAGE = "Reader with this ID [%d] doesn't exist";

    public ReaderNotFoundException(String message) {
        super(message);
    }

    public ReaderNotFoundException(Long id) {
        super(String.format(DEFAULT_MESSAGE, id));
    }

    public ReaderNotFoundException(Long id, Throwable cause) {
        super(String.format(DEFAULT_MESSAGE, id), cause);
    }

    public ReaderNotFoundException(Throwable cause) {
        super(cause);
    }
}
