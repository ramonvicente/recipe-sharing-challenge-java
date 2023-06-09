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

    /**
     * Update {@link Recipe} by id in the database provided by {@link RecipeRequest}.
     * @param recipeId String containing recipe id.
     * @param recipeRequest DTO containing the {@link Recipe} data to be updated.
     * @return {@link net.azeti.challenge.recipe.dto.RecipeResponse} DTO containing the updated {@link Recipe} data.
     */
    RecipeResponse update(String recipeId, RecipeRequest recipeRequest);

    /**
     * Delete {@link Recipe} by id in the database.
     * @param recipeId String containing recipe id.
     * @return {@link net.azeti.challenge.recipe.dto.RecipeResponse} DTO containing the found {@link Recipe} data.
     */
    RecipeResponse delete(String recipeId);

    /**
     * Get List of {@link Recipe} by username from the database.
     * @param username String containing recipe username.
     * @return List of {@link net.azeti.challenge.recipe.dto.RecipeResponse} DTO containing the found {@link Recipe} data.
     */
    List<RecipeResponse> getByUser(String username);
}
