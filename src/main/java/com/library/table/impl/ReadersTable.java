package com.library.table.impl;

import com.library.model.Reader;
import com.library.table.EntityTable;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

import java.util.ArrayList;
import java.util.List;

public class ReadersTable implements EntityTable<Reader> {

    private final List<Reader> entities;

    public ReadersTable(List<Reader> entities) {
        this.entities = entities;
    }

    @Override
    public String render(int width) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"id", "name", "email", "birthday"});
        for (Reader reader : entities) {
            data.add(toRow(reader));
        }
        ArrayTableModel model = new ArrayTableModel(data.toArray(new String[0][0]));
        TableBuilder table = new TableBuilder(model);
        table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
        return table.build().render(width);
    }

    @Override
    public String[] toRow(Reader entity) {
        return new String[]{
                String.valueOf(entity.getId()),
                entity.getName(),
                entity.getEmail(),
                entity.getBirthday().toString()
        };
    }
}
