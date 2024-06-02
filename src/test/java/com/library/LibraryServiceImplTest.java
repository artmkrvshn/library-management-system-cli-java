package com.library;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.ReaderService;
import com.library.service.impl.LibraryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl service;

    @Mock
    private BookService bookService;

    @Mock
    private ReaderService readerService;

    @Test
    void contextLoads() {
        assertNotNull(bookService);
        assertNotNull(readerService);
        assertNotNull(service);
    }

    @Test
    void borrowBooKTest() {
        Long readerId = 1L;
        Long bookId = 1L;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Test Book");
        book.setReader(null);

        when(bookService.findByIdFetch(bookId)).thenReturn(book);

        service.borrowBook(readerId, bookId);

        verify(bookService, times(1)).findByIdFetch(bookId);
        verify(bookService, times(1)).findByIdFetch(readerId);
    }
}
