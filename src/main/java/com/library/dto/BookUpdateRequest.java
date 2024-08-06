package com.library.dto;

public record BookUpdateRequest(
        String title,
        Short published,
        String author) {
}