package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
import net.azeti.challenge.recipe.model.Recipe;

import java.util.List;

// This class assumes the Recipe's id is a Long, this can be changed if needed.
public interface RecipeService {

    /**
     * Creates a new {@link Recipe} in the database from the provided {@link RecipeRequest}.
     * @param recipeRequest DTO containing recipe data.
     * @return {@link RecipeIdResponse} DTO containing the saved {@link Recipe} data.
     */
    RecipeIdResponse create(RecipeRequest recipeRequest);

    /**
     * Get {@link Recipe} by id from the database provided by {@link RecipeRequest}.
     * @param recipeId String containing recipe id.
     * @return {@link net.azeti.challenge.recipe.dto.RecipeResponse} DTO containing the found {@link Recipe} data.
     */
    RecipeResponse getById(String recipeId);

    Recipe update(String id, Recipe recipe);

    Recipe delete(String id);

    List<Recipe> getByUser(String username);
}
