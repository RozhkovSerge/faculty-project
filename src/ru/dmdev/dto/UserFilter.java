package ru.dmdev.dto;

public record UserFilter(int limit, int offset, String firstName, String lastName, String email) {
}
