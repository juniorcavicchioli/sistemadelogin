package br.com.loginapi.dto.update;

public record AddressUpdateDTO(
        String street,
        Integer number,
        String neightborhood,
        String city,
        String state,
        String zipcode,
        String country,
        String complement
) {
}
