package net.azeti.challenge.recipe.dto.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank
    @Email(message = "Email format is not valid.", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
