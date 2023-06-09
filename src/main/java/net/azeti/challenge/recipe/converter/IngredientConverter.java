package net.azeti.challenge.recipe.converter;

import net.azeti.challenge.recipe.dto.IngredientRequest;
import net.azeti.challenge.recipe.dto.IngredientResponse;
import net.azeti.challenge.recipe.model.Ingredient;
import net.azeti.challenge.recipe.model.Recipe;

import java.util.Set;
import java.util.stream.Collectors;

public class IngredientConverter {

    public static Set<Ingredient> toIngredients(Set<IngredientRequest> request, Recipe recipe) {
        if(request.isEmpty()) return Set.of();
        return request.stream()
                .map(i -> Ingredient.builder()
                        .value(i.getValue())
                        .unity(i.getUnity())
                        .type(i.getType())
                        .recipe(recipe)
                        .build()
                ).collect(Collectors.toSet());
    }

    public static Set<Ingredient> toIngredientsT(Set<Ingredient> request, Recipe recipe) {
        if(request.isEmpty()) return Set.of();
        return request.stream()
                .map(i -> Ingredient.builder()
                        .value(i.getValue())
                        .unity(i.getUnity())
                        .type(i.getType())
                        .recipe(recipe)
                        .build()
                ).collect(Collectors.toSet());
    }

    public static Set<IngredientResponse> toIngredientResponse(Set<Ingredient> ingredients) {
        if(ingredients.isEmpty()) {
            return Set.of();
        }
        return ingredients.stream()
                .map(i -> IngredientResponse.builder()
                        .value(i.getValue())
                        .unity(i.getUnity())
                        .type(i.getType())
                        .id(i.getId())
                        .build()
                ).collect(Collectors.toSet());
    }
}
