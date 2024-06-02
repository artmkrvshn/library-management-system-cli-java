package com.library.service;

import com.library.dto.BookUpdateRequest;
import com.library.model.Book;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BookService {

    Long save(Book book);

    default List<Book> findAll() {
        return findAll(Sort.by("id"));
    }

    List<Book> findAll(Sort sort);

    List<Book> findAllFreeBooks(Sort sort);

    default List<Book> findAllFreeBooks() {
        return findAllFreeBooks(Sort.by("id"));
    }

    List<Book> findAllBorrowedBooks(Sort sort);

    default List<Book> findAllBorrowedBooks() {
        return findAllBorrowedBooks(Sort.by("id"));
    }

    Book findById(Long id);

    Book findByIdFetch(Long id);

    void update(Long id, BookUpdateRequest request);

    void delete(Long id);
}
