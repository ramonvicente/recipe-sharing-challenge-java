package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    public static final String ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL = "recipe should not be null.";
    public static final String ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK = "id should not be null or blank";
    public static final String ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK = "username should not be null or blank.";

    private final RecipeRepository recipeRepository;

    @Override
    public RecipeIdResponse create(RecipeRequest recipeRequest) {
        if(recipeRequest == null) {
            throw new IllegalArgumentException(ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
        }
        Recipe recipe = RecipeConverter.toRecipe(recipeRequest);
        return RecipeConverter.toRecipeIdResponse(recipeRepository.saveAndFlush(recipe));
    }

    @Override
    public RecipeResponse getById(String recipeId) {
        if(recipeId == null || recipeId.isBlank())  {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        return RecipeConverter.toRecipeResponse(recipe);
    }

    @Override
    public RecipeResponse update(String recipeId, RecipeRequest request) {
        if(recipeId == null || recipeId.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        if(request == null) {
            throw new IllegalArgumentException(ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
        }
        if(recipeRepository.findById(recipeId).isPresent()) {
            Recipe recipe = RecipeConverter.toRecipe(request);
            recipe.setId(recipeId);
            Recipe savedRecipe = recipeRepository.save(recipe);
            return RecipeConverter.toRecipeResponse(savedRecipe);
        }
        return null;
    }

    @Override
    public RecipeResponse delete(String id) {
        if(id == null  || id.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return RecipeConverter.toRecipeResponse(recipe.get());
        }
        return null;
    }

    @Override
    public List<Recipe> getByUser(String username) {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        return recipeRepository.findRecipesByUsername(username);
    }
}
