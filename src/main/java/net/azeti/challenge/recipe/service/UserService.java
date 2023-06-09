package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.auth.Registration;
import net.azeti.challenge.recipe.dto.auth.RegistrationResult;
import net.azeti.challenge.recipe.user.Login;
import net.azeti.challenge.recipe.user.Token;

import java.util.Optional;

public interface UserService {

    /**
     * Creates a new {@link net.azeti.challenge.recipe.model.User} in the database from the provided {@link RecipeRequest}.
     * @param registration DTO containing user data to be registered.
     * @return {@link RegistrationResult} DTO containing the registered {@link net.azeti.challenge.recipe.model.User} data.
     */
    RegistrationResult registerUser(Registration registration);

    Optional<Token> login(Login login);
}
