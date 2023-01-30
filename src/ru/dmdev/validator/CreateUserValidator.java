package ru.dmdev.validator;

import ru.dmdev.dto.CreateUserDto;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto userDto) {
        ValidationResult validationResult = new ValidationResult();
        if(userDto.getEmail() == null) {
            validationResult.add(Error.of("login validation error", "email can't be empty"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
