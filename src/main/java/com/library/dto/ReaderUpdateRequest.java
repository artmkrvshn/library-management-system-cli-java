package com.library.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public final class ReaderUpdateRequest {
    private final String name;
    private final String email;
    private final LocalDate birthday;
}