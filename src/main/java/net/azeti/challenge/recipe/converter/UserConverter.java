package net.azeti.challenge.recipe.converter;

import net.azeti.challenge.recipe.dto.auth.RegistrationRequest;
import net.azeti.challenge.recipe.model.User;

public class UserConverter {

    public static User toUser(RegistrationRequest request) {
        if(request == null) return null;
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
