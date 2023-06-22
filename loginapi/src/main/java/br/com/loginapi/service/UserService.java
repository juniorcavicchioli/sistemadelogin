package br.com.loginapi.service;

import br.com.loginapi.dto.UserProfileResponseDTO;
import br.com.loginapi.dto.UserRegisterDTO;
import br.com.loginapi.dto.UserResponseDTO;
import br.com.loginapi.model.User;
import br.com.loginapi.model.UserProfile;
import br.com.loginapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileService userProfileService;

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(UserRegisterDTO userRegisterDTO) {
        UserProfile userProfile =  new UserProfile();

        userProfile = userProfileService.registerUserProfile(userProfile);

        User user = new User(null, null, userRegisterDTO.email(), userRegisterDTO.password(), userProfile);
        user = userRepository.save(user);
        return user;
    }

    public UserResponseDTO convertToUserResponseDTO(User user) {
        UserProfileResponseDTO userProfileResponseDTO = userProfileService.convertToUserProfileResponseDTO(user.getUserProfile());
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), userProfileResponseDTO);
    }

    public List<UserResponseDTO> getListOfUserResponseDTO() {
        List<UserResponseDTO> list = new ArrayList<>();

        for (User user : userRepository.findAll())
            list.add(convertToUserResponseDTO(user));
        return list;
    }

    public User updateUser(Long id, User user) {
        // TODO : ajustar o recebimento de um usuario para um dto.
        User findedUser = getUserById(id).get();
        user.setId(id);
        user.getUserProfile().setId(findedUser.getUserProfile().getId());
        user.getUserProfile().getAddress().setId(findedUser.getUserProfile().getAddress().getId());
        return userRepository.save(user);
    }

    public void destroyUser(User user) {
        userRepository.delete(user);
    }
}
