package com.library.table.impl;

import com.library.model.Book;
import com.library.table.EntityTable;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

import java.util.ArrayList;
import java.util.List;

public class BooksTable implements EntityTable<Book> {

    private final List<Book> entities;

    public BooksTable(List<Book> entities) {
        this.entities = entities;
    }

    @Override
    public String render(int width) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id", "title", "published", "author", "reader_id"});
        for (Book book : entities) {
            data.add(toRow(book));
        }
        ArrayTableModel model = new ArrayTableModel(data.toArray(new String[0][0]));
        TableBuilder table = new TableBuilder(model);
        table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
        return table.build().render(width);
    }

    @Override
    public String[] toRow(Book entity) {
        return new String[]{
                String.valueOf(entity.getId()),
                entity.getTitle(),
                entity.getPublished().toString(),
                entity.getAuthor(),
                entity.getReader() != null ? entity.getReader().getId().toString() : "null"
        };
    }
}