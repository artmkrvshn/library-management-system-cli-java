package com.library.command;

import java.time.LocalDate;

public interface ReaderCommands {

    String create(String name, String email, LocalDate birthday);

    String readAll();

    String readById(Long id);

    String updateById(Long id, String name, String email, LocalDate birthday);

    String deleteById(Long id);

}