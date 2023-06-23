package br.com.loginapi.dto.update;

import br.com.loginapi.dto.response.UserProfileResponseDTO;

public record UserUpdateDTO(
        String name,
        String email,
        UserProfileUpdateDTO userProfile
) {
}
