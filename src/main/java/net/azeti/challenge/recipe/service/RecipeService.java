package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.model.Recipe;

import java.util.List;
import java.util.Optional;

// This class assumes the Recipe's id is a Long, this can be changed if needed.
public interface RecipeService {

    /**
     * Creates a new {@link Recipe} in the database from the provided {@link RecipeRequest}.
     * @param recipeRequest DTO containing recipe data.
     * @return {@link net.azeti.challenge.recipe.dto.RecipeResponse} DTO containing the saved {@link Recipe} data.
     */
    RecipeIdResponse create(RecipeRequest recipeRequest);

    Optional<Recipe> getById(String id);

    Recipe update(String id, Recipe recipe);

    Recipe delete(String id);

    List<Recipe> getByUser(String username);
}
