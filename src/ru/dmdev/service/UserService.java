package ru.dmdev.service;

import ru.dmdev.dao.UserDao;
import ru.dmdev.dto.CreateUserDto;
import ru.dmdev.dto.UserDto;
import ru.dmdev.entity.User;
import ru.dmdev.exception.ValidationException;
import ru.dmdev.mapper.CreateUserMapper;
import ru.dmdev.validator.CreateUserValidator;
import ru.dmdev.validator.ValidationResult;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


public class UserService {

    private final static UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();

    private UserService() {
    }


    public Long create(CreateUserDto userDto) {
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if(!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        User userEntity = createUserMapper.mapFrom(userDto);
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public List<UserDto> findAll() {
        return userDao.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getAddress()))
                .collect(toList());
    }

    public UserDto findById(Long userId) {
        Optional<User> optionalUser = userDao.findById(userId);
        UserDto userDto = null;
        if (optionalUser.isPresent()) {
            userDto = new UserDto(
                    optionalUser.get().getId(),
                    optionalUser.get().getFirstName(),
                    optionalUser.get().getLastName(),
                    optionalUser.get().getEmail(),
                    optionalUser.get().getAddress()
            );
        }
        return userDto;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
