package net.azeti.challenge.recipe.converter;

import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.model.Recipe;

public class RecipeConverter {

    public static Recipe toRecipe(RecipeRequest request) {
        return Recipe.builder()
                .description(request.getDescription())
                .title(request.getTitle())
                .instructions(request.getInstructions())
                .username(request.getUsername())
                .serving(request.getServing())
                .build();
    }
}
