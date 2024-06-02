package com.library.command.impl;

import com.library.command.BookCommands;
import com.library.dto.BookUpdateRequest;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.table.impl.BooksTable;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.List;

import static org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE;

@Command(command = "books")
public class BookCommandsImpl implements BookCommands {

    private final BookService bookService;

    public BookCommandsImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Command(command = "create", description = "Create a book")
    public String create(
            @Option(longNames = {"title"}, required = true, arity = EXACTLY_ONE) String title,
            @Option(longNames = {"published"}, required = true, arity = EXACTLY_ONE) Short published,
            @Option(longNames = {"author"}, required = true, arity = EXACTLY_ONE) String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublished(published);
        bookService.save(book);
        return "Book successfully created";
    }

    @Override
    @Command(description = "Read all books")
    public String readAll() {
        List<Book> books = bookService.findAll();
        return new BooksTable(books).render(100);
    }

    @Override
    @Command(command = "free", description = "Read all free books")
    public String readAllFreeBooks() {
        List<Book> books = bookService.findAllFreeBooks();
        return new BooksTable(books).render(100);
    }

    @Override
    @Command(command = "borrowed", description = "Read all borrowed books")
    public String readAllBorrowedBooks() {
        List<Book> books = bookService.findAllBorrowedBooks();
        return new BooksTable(books).render(100);
    }

    @Override
    @Command(command = "id", description = "Read a book by id")
    public String readById(@Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id) {
        Book book = bookService.findByIdFetch(id);
        return String.format("%d, %s, %d, %s, %s",
                book.getId(), book.getTitle(), book.getPublished(), book.getAuthor(),
                book.getReader() != null ? book.getReader().getId() : "Free");
    }

    @Override
    @Command(command = {"update"}, description = "Update a book by id")
    public String updateById(
            @Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id,
            @Option(longNames = {"title"}, arity = EXACTLY_ONE) String title,
            @Option(longNames = {"published"}, arity = EXACTLY_ONE) Short published,
            @Option(longNames = {"author"}, arity = EXACTLY_ONE) String author) {
        BookUpdateRequest request = new BookUpdateRequest(title, published, author);
        bookService.update(id, request);
        return "Book updated";
    }

    @Override
    @Command(command = {"delete"}, description = "Delete a book by id")
    public String deleteById(@Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id) {
        bookService.delete(id);
        return "Book was deleted";
    }
}
