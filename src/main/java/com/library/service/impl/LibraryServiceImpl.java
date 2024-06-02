package com.library.service.impl;

import com.library.model.Book;
import com.library.model.Reader;
import com.library.service.BookService;
import com.library.service.LibraryService;
import com.library.service.ReaderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final ReaderService readerService;
    private final BookService bookService;

    public LibraryServiceImpl(ReaderService readerService, BookService bookService) {
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @Transactional
    @Override
    public void borrowBook(Long readerId, Long bookId) {
        Book bookToBorrow = bookService.findByIdFetch(bookId);
        if (bookToBorrow.getReader() == null) {
            Reader reader = readerService.findById(readerId);
            bookToBorrow.setReader(reader);
        } else {
            // TODO: create a custom exception
            Reader reader = bookToBorrow.getReader();
            throw new RuntimeException(
                    String.format("A book %s [%d] has a reader %s [%d]",
                            bookToBorrow.getTitle(), bookId, reader.getName(), reader.getId())
            );
        }
    }

    @Transactional
    @Override
    public void returnBook(Long readerId, Long bookId) {
        Reader reader = readerService.findById(readerId);
        Book bookToReturn = bookService.findByIdFetch(bookId);

        if (bookToReturn.getReader() == reader) {
            bookToReturn.setReader(null);
        } else {
            // TODO: create a custom exception
            throw new RuntimeException(
                    String.format("A reader %s [%d] doesn't have %s [%d] book",
                            reader.getName(), readerId, bookToReturn.getTitle(), bookId)
            );
        }
    }
}