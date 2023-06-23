package br.com.loginapi.controller;


import br.com.loginapi.dto.UserRegisterDTO;
import br.com.loginapi.dto.response.UserResponseDTO;
import br.com.loginapi.dto.update.UserUpdateDTO;
import br.com.loginapi.model.User;
import br.com.loginapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    private User getUser(Long id) {
        return userService.getUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not finded")
        );
    }
    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRegisterDTO userRegisterDTO, BindingResult result){
        UserResponseDTO user = userService.convertToUserResponseDTO(userService.registerUser(userRegisterDTO));
        return ResponseEntity
                .created(URI.create("api/user/" + user.id()))
                .body(user);
    }

    @GetMapping("{id}")
    public UserResponseDTO show(@PathVariable Long id){
        var user = getUser(id);
        return userService.convertToUserResponseDTO(user);
    }

    @GetMapping()
    public List<UserResponseDTO> index(){ //@RequestParam String busca
        return  userService.getListOfUserResponseDTO();
    }

    @PutMapping("{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userDTO, BindingResult result){
        getUser(id);
        User user = userService.convertUpdateDTOToUser(userDTO);
        return userService.convertToUserResponseDTO(userService.updateUser(id,user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id){
        var user = getUser(id);
        userService.destroyUser(user);
        return ResponseEntity.noContent().build();
    }
}
