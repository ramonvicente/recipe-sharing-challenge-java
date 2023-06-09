package net.azeti.challenge.recipe.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
import net.azeti.challenge.recipe.model.Ingredient;
import net.azeti.challenge.recipe.model.Recipe;

import java.util.Optional;
import java.util.Set;

public class RecipeConverter {

    public static Recipe toRecipe(RecipeRequest request) {
        if(request == null) {
            return null;
        }
        return Recipe.builder()
                .description(request.getDescription())
                .title(request.getTitle())
                .instructions(request.getInstructions())
                .username(request.getUsername())
                .serving(request.getServing())
                .build();
    }

    public static RecipeResponse toRecipeResponse(Optional<Recipe> recipe) {
        if(recipe.isPresent()) {
            Recipe validRecipe = recipe.get();
            return RecipeResponse.builder()
                    .id(validRecipe.getId())
                    .description(validRecipe.getDescription())
                    .title(validRecipe.getTitle())
                    .instructions(validRecipe.getInstructions())
                    .username(validRecipe.getUsername())
                    .serving(validRecipe.getServing())
                    .ingredients(IngredientConverter.toIngredientResponse(validRecipe.getIngredients()))
                    .build();
        }
        return null;
    }

    public static RecipeResponse toRecipeResponse(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        return RecipeResponse.builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .title(recipe.getTitle())
                .instructions(recipe.getInstructions())
                .username(recipe.getUsername())
                .serving(recipe.getServing())
                .build();
    }

    public static RecipeIdResponse toRecipeIdResponse(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        return RecipeIdResponse.builder()
                .id(recipe.getId())
                .build();
    }

    public static String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
