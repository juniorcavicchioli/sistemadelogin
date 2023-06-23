package br.com.loginapi.dto.response;

import br.com.loginapi.dto.response.AddressResponseDTO;

public record UserProfileResponseDTO(
        Long id,
        String phoneNumber,
        String bio,
        AddressResponseDTO address
) {

}
