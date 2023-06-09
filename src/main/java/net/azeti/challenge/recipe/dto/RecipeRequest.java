package net.azeti.challenge.recipe.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class RecipeRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String username;
    private String description;
    @NotBlank
    private String instructions;
    private int serving;
    @NotEmpty
    private Set<IngredientRequest> ingredients;
}
