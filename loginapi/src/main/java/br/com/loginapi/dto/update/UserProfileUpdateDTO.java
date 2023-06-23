package br.com.loginapi.dto.update;

import br.com.loginapi.dto.response.AddressResponseDTO;

public record UserProfileUpdateDTO(
        String phoneNumber,
        String bio,
        AddressUpdateDTO address
) {
}
