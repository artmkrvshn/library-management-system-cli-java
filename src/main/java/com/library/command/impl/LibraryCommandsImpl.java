package com.library.command.impl;


import com.library.command.LibraryCommands;
import com.library.service.LibraryService;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import static org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE;

@Command(command = "library")
public class LibraryCommandsImpl implements LibraryCommands {

    private final LibraryService libraryService;

    public LibraryCommandsImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    @Command(command = "borrow", description = "Borrow a book")
    public String borrowBook(
            @Option(longNames = {"readerId"}, required = true, arity = EXACTLY_ONE) Long readerId,
            @Option(longNames = {"bookId"}, required = true, arity = EXACTLY_ONE) Long bookId) {
        libraryService.borrowBook(readerId, bookId);
        return "A book was borrowed";
    }

    @Command(command = {"return"}, description = "Return a book")
    @Override
    public String returnBook(
            @Option(longNames = {"readerId"}, required = true, arity = EXACTLY_ONE) Long readerId,
            @Option(longNames = {"bookId"}, required = true, arity = EXACTLY_ONE) Long bookId) {
        libraryService.returnBook(readerId, bookId);
        return "A book was returned";
    }
}