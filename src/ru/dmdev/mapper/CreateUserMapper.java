package ru.dmdev.mapper;

import ru.dmdev.dto.CreateUserDto;
import ru.dmdev.entity.Role;
import ru.dmdev.entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private static final AddressMapper addressMapper = AddressMapper.getInstance();

    @Override
    public User mapFrom(CreateUserDto object) {

        return User
                .builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .password(object.getPassword())
                .address(addressMapper.mapFrom(object.getAddress()))
                .role(Role.builder().name(object.getRole()).build())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
