package net.azeti.challenge.recipe.dto;

import lombok.Builder;
import lombok.Data;
import net.azeti.challenge.recipe.model.Ingredient;

import java.util.Set;

@Data
@Builder
public class RecipeResponse {
    private String id;
    private String title;
    private String username;
    private String description;
    private String instructions;
    private int serving;
    private Set<IngredientResponse> ingredients;
}
