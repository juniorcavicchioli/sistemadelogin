package br.com.loginapi.service;

import br.com.loginapi.dto.AddressResponseDTO;
import br.com.loginapi.dto.UserProfileResponseDTO;
import br.com.loginapi.dto.UserResponseDTO;
import br.com.loginapi.model.User;
import br.com.loginapi.model.UserProfile;
import br.com.loginapi.repository.AddressRepository;
import br.com.loginapi.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private AddressService addressService;

    public UserProfile registerUserProfile(UserProfile userProfile){
       return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(Long id, UserProfile userProfile) {
        userProfile.setId(id);
        return userProfileRepository.save(userProfile);
    }

    public UserProfileResponseDTO convertToUserProfileResponseDTO(UserProfile userProfile){
        AddressResponseDTO addressResponseDTO;
        if (userProfile.getAddress() == null) addressResponseDTO = null;
        else addressResponseDTO = addressService.convertToAddressResponseDTO(userProfile.getAddress());

        return new UserProfileResponseDTO(userProfile.getId(), userProfile.getPhoneNumber(), userProfile.getBio(), addressResponseDTO);
    }
}
