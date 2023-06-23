package br.com.loginapi.dto.response;

public record AddressResponseDTO(
        Long id,
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
