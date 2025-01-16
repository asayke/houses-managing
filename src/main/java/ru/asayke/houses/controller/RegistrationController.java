package ru.asayke.houses.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.asayke.houses.dto.RegistrationRequest;
import ru.asayke.houses.model.User;
import ru.asayke.houses.service.UserService;
import ru.asayke.houses.util.ErrorsUtil;
import ru.asayke.houses.util.UserException;
import ru.asayke.houses.util.UserExceptionResponse;
import ru.asayke.houses.util.UserValidator;
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом + в нём же прописать final

@RestController
@RequestMapping(value = "/api/registration/")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationRequest request, BindingResult bindingResult) {
        //TODO перенести логику в сервисы
        User userToAdd = convertToUser(request);
        userValidator.validate(userToAdd, bindingResult);

        if (bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        userService.register(userToAdd);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    //TODO для такого есть @ControllerAdvice
    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(UserException e) {
        UserExceptionResponse response = new UserExceptionResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    //TODO перенести логику в сервисы
    private User convertToUser(RegistrationRequest regDTO) {
        return mapper.map(regDTO, User.class);
    }
}