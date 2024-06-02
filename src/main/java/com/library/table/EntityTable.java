package com.library.table;

public interface EntityTable<T> {

    String render(int width);

    String[] toRow(T entity);

}