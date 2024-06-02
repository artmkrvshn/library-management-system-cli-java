package com.library.service;

public interface LibraryService {

    void borrowBook(Long readerId, Long bookId);

    void returnBook(Long readerId, Long bookId);

}