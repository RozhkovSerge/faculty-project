package ru.dmdev.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressDto {
     String city;
     String street;
     String house;
     String apartment;
}
