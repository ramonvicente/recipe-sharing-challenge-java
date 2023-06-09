package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.auth.RegistrationRequest;
import net.azeti.challenge.recipe.dto.auth.RegistrationResult;
import net.azeti.challenge.recipe.user.Login;
import net.azeti.challenge.recipe.user.Token;

import java.util.Optional;

public interface UserService {

    /**
     * Creates a new {@link net.azeti.challenge.recipe.model.User} in the database from the provided {@link RecipeRequest}.
     * @param registrationRequest DTO containing user data to be registered.
     * @return {@link RegistrationResult} DTO containing the registered {@link net.azeti.challenge.recipe.model.User} data.
     */
    RegistrationResult registerUser(RegistrationRequest registrationRequest);

    Optional<Token> login(Login login);
}
