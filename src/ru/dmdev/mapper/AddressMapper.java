package ru.dmdev.mapper;

import ru.dmdev.dto.AddressDto;
import ru.dmdev.entity.Address;

public class AddressMapper implements Mapper<AddressDto, Address>{

    private static final AddressMapper INSTANCE = new AddressMapper();
    @Override
    public Address mapFrom(AddressDto object) {
        return Address
                .builder()
                .city(object.getCity())
                .street(object.getStreet())
                .house(object.getHouse())
                .apartment(object.getApartment())
                .build();
    }

    public static AddressMapper getInstance() {
        return INSTANCE;
    }
}
