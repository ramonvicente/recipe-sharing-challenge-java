package net.azeti.challenge.recipe.controller;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.service.UserService;
import net.azeti.challenge.recipe.service.UserServiceImpl;
import net.azeti.challenge.recipe.dto.auth.RegistrationRequest;
import net.azeti.challenge.recipe.dto.auth.RegistrationResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResult> registerUser(@Valid @RequestBody RegistrationRequest request) {
        RegistrationResult result = userService.registerUser(request);
        if(userExist(result)) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private static boolean userExist(RegistrationResult result) {
        return result.getMessage().equals(UserServiceImpl.RESPONSE_MESSAGE_EMAIL_ALREADY_EXIST)
                || result.getMessage().equals(UserServiceImpl.RESPONSE_MESSAGE_USER_ALREADY_EXIST);
    }
}
