package br.com.loginapi.dto.response;

import br.com.loginapi.dto.response.UserProfileResponseDTO;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        UserProfileResponseDTO userProfile
) {
}
