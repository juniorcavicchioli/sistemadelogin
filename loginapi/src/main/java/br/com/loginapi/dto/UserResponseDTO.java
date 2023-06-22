package br.com.loginapi.dto;

import br.com.loginapi.model.UserProfile;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        UserProfileResponseDTO userProfile
) {
}
