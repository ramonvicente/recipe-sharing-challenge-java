package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.model.User;
import net.azeti.challenge.recipe.repository.UserRepository;
import net.azeti.challenge.recipe.dto.auth.Registration;
import net.azeti.challenge.recipe.dto.auth.RegistrationResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String RESPONSE_MESSAGE_USER_ALREADY_EXIST = "User %s already exist.";
    public static final String RESPONSE_MESSAGE_EMAIL_ALREADY_EXIST = "User with email %s was already exist.";
    public static final String ERROR_MESSAGE_REGISTRATION_NULL = "registration should not be null.";

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public RegistrationResult registerUser(Registration registration) {
        if(registration == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_REGISTRATION_NULL);
        }
        String message;
        if (userRepository.findByUsername(registration.getUsername()) != null) {
            message = String.format(RESPONSE_MESSAGE_USER_ALREADY_EXIST, registration.getUsername());
            return RegistrationResult.builder()
                    .message(message)
                    .build();
        }
        if (userRepository.findByEmail(registration.getEmail()) != null) {
            message = String.format(RESPONSE_MESSAGE_EMAIL_ALREADY_EXIST, registration.getUsername());
            return RegistrationResult.builder()
                    .message(message)
                    .build();
        }
        User user = User.builder()
                .email(registration.getEmail())
                .username(registration.getUsername())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        User newUser = userRepository.save(user);
        message = String.format("User %s was registered successfully.", newUser.getUsername());
        return RegistrationResult.builder()
                .message(message)
                .build();
    }
}
