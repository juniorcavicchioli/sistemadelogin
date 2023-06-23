package br.com.loginapi.service;

import br.com.loginapi.dto.response.AddressResponseDTO;
import br.com.loginapi.dto.response.UserProfileResponseDTO;
import br.com.loginapi.dto.update.UserProfileUpdateDTO;
import br.com.loginapi.model.UserProfile;
import br.com.loginapi.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private AddressService addressService;

    public Optional<UserProfile> getUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public UserProfile registerUserProfile(UserProfile userProfile){
       return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(Long id, UserProfile userProfile) {
        UserProfile findedUserProfile = getUserProfileById(id).get();
        findedUserProfile.setPhoneNumber(userProfile.getPhoneNumber());
        findedUserProfile.setBio(userProfile.getBio());
        findedUserProfile.setAddress(
                addressService.updateAddress(
                        id,
                        userProfile.getAddress()
                        )
        );
        return userProfileRepository.save(findedUserProfile);
    }

    public UserProfileResponseDTO convertToUserProfileResponseDTO(UserProfile userProfile){
        AddressResponseDTO addressResponseDTO;
        if (userProfile.getAddress() == null) addressResponseDTO = null;
        else addressResponseDTO = addressService.convertToAddressResponseDTO(userProfile.getAddress());

        return new UserProfileResponseDTO(userProfile.getId(), userProfile.getPhoneNumber(), userProfile.getBio(), addressResponseDTO);
    }

    public UserProfile convertUpdateDTOToUserService(UserProfileUpdateDTO userProfileUpdateDTO) {
        return new UserProfile(
                null,
                userProfileUpdateDTO.phoneNumber(),
                userProfileUpdateDTO.bio(),
                addressService.convertUpdateDTOToAddress(userProfileUpdateDTO.address()),
                null
        );
    }
}
