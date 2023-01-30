package ru.dmdev.validator;

import lombok.Builder;
import lombok.Value;

@Value(staticConstructor = "of")
public class Error {

    String code;
    String message;
}
