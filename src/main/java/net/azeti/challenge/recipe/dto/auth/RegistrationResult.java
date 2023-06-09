package net.azeti.challenge.recipe.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResult {
    private String message;
}
