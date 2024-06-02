package com.library.command;

public interface LibraryCommands {

    String borrowBook(Long readerId, Long bookId);

    String returnBook(Long readerId, Long bookId);

}
