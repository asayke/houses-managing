package ru.asayke.houses.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asayke.houses.dto.AuthenticationRequest;
import ru.asayke.houses.model.User;
import ru.asayke.houses.security.jwt.JwtTokenProvider;
import ru.asayke.houses.service.UserService;

import java.util.HashMap;
import java.util.Map;
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом + в нём же прописать final

@RestController
@RequestMapping(value = "/api/login/")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthenticationRequest requestDTO) {
        //TODO перенести логику в сервисы
        try {
            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}