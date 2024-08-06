package com.library.dto;

import java.time.LocalDate;

public record ReaderUpdateRequest(
        String name,
        String email,
        LocalDate birthday) {
}