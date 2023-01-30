package ru.dmdev.validator;

public interface Validator <T> {

    ValidationResult isValid(T obj);
}
