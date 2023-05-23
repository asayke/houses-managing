package ru.asayke.houses.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asayke.houses.dto.UserDTO;
import ru.asayke.houses.dto.fieldsDTO.user.AgeDTO;
import ru.asayke.houses.dto.fieldsDTO.user.NameDTO;
import ru.asayke.houses.model.User;
import ru.asayke.houses.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping(value = "/user/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping("get-user")
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        String username = principal.getName();

        User user = userService.findByUsername(username);

        return ResponseEntity.ok(convertToUserDTO(user));
    }

    @PostMapping("/update-name")
    public ResponseEntity<HttpStatus> updateFirstName(@RequestBody NameDTO nameDTO, Principal principal) {
        String username = principal.getName();

        userService.updateUserName(username, nameDTO.getName());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-age")
    public ResponseEntity<HttpStatus> updateColor(@RequestBody AgeDTO ageDTO, Principal principal) {
        String username = principal.getName();

        userService.updateUserAge(username, ageDTO.getAge());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<HttpStatus> deleteUser(Principal principal) {
        String username = principal.getName();

        userService.deleteUser(username);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private UserDTO convertToUserDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
}