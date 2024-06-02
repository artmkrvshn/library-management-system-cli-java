package com.library.dto;

import lombok.Data;

@Data
public final class BookUpdateRequest {
    private final String title;
    private final Short published;
    private final String author;
}