package com.library.command;

public interface BookCommands {

    String create(String title, Short published, String author);

    String readAll();

    String readAllFreeBooks();

    String readAllBorrowedBooks();

    String readById(Long id);

    String updateById(Long id, String title, Short published, String author);

    String deleteById(Long id);

}
