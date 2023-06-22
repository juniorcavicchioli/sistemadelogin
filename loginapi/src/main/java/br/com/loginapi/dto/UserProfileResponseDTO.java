package br.com.loginapi.dto;

import br.com.loginapi.model.Address;

public record UserProfileResponseDTO(
        Long id,
        String phoneNumber,
        String bio,
        AddressResponseDTO address
) {

}
