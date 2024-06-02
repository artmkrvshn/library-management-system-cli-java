package com.library.command.impl;

import com.library.command.ReaderCommands;
import com.library.dto.ReaderUpdateRequest;
import com.library.model.Reader;
import com.library.service.ReaderService;
import com.library.table.impl.BooksTable;
import com.library.table.impl.ReadersTable;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.shell.command.CommandRegistration.OptionArity.EXACTLY_ONE;

@Command(command = "readers")
public class ReaderCommandsImpl implements ReaderCommands {

    private final ReaderService readerService;

    public ReaderCommandsImpl(ReaderService readerService) {
        this.readerService = readerService;
    }

    @Override
    @Command(command = "create", description = "Create a reader")
    public String create(
            @Option(longNames = {"name"}, required = true, arity = EXACTLY_ONE) String name,
            @Option(longNames = {"email"}, required = true, arity = EXACTLY_ONE) String email,
            @Option(longNames = {"birthday"}, required = true, arity = EXACTLY_ONE) LocalDate birthday) {
        Reader reader = new Reader();
        reader.setName(name);
        reader.setEmail(email);
        reader.setBirthday(birthday);
        readerService.save(reader);
        return "Reader successfully created";
    }

    @Override
    @Command(description = "Read all readers")
    public String readAll() {
        List<Reader> readers = readerService.findAll();
        return new ReadersTable(readers).render(100);
    }

    @Override
    @Command(command = "id", description = "Read a reader by id")
    public String readById(@Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id) {
        Reader reader = readerService.findByIdFetch(id);
        String readerString = String.format("%d, %s, %s, %s", reader.getId(), reader.getName(), reader.getEmail(), reader.getBirthday());
        StringBuilder sb = new StringBuilder(readerString).append(System.lineSeparator());
        if (!reader.getBooks().isEmpty()) {
            sb.append(new BooksTable(reader.getBooks()).render(100));
        } else {
            sb.append("This reader doesn't have books");
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    @Command(command = "update", description = "Update a reader by id")
    public String updateById(
            @Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id,
            @Option(longNames = {"name"}, arity = EXACTLY_ONE) String name,
            @Option(longNames = {"email"}, arity = EXACTLY_ONE) String email,
            @Option(longNames = {"birthday"}, arity = EXACTLY_ONE) LocalDate birthday) {
        ReaderUpdateRequest request = new ReaderUpdateRequest(name, email, birthday);
        readerService.update(id, request);
        return "Reader updated";
    }

    @Command(command = {"delete"}, description = "Delete a reader by id")
    @Override
    public String deleteById(@Option(longNames = {"id"}, required = true, arity = EXACTLY_ONE) Long id) {
        readerService.delete(id);
        return "Reader was deleted";
    }
}