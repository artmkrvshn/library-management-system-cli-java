package com.library.service.impl;

import com.library.dto.BookUpdateRequest;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Long save(Book book) {
        return repository.save(book).getId();
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return repository.findAllFetch();
    }

    @Override
    public List<Book> findAllFreeBooks(Sort sort) {
        return repository.findFreeBooksOrderById(sort);
    }

    @Override
    public List<Book> findAllBorrowedBooks(Sort sort) {
        return repository.findBorrowedBooksOrderById(sort);
    }

    @Override
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book findByIdFetch(Long id) {
        return repository.findBookByIdFetch(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional
    @Override
    public void update(Long id, BookUpdateRequest request) {
        Book book = repository.findBookByIdFetch(id).orElseThrow(() -> new BookNotFoundException(id));
        if (request.title() != null) book.setTitle(request.title());
        if (request.published() != null) book.setPublished(request.published());
        if (request.author() != null) book.setAuthor(request.author());
        repository.save(book);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}