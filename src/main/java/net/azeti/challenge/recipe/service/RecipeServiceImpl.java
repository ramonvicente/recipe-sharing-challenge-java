package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
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
    public Recipe create(Recipe recipe) {
        if(recipe == null) {
            throw new IllegalArgumentException(ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
        }
        return recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public Optional<Recipe> getById(String id) {
        if(id == null || id.isBlank())  {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe update(String id, Recipe recipe) {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        if(recipe == null) {
            throw new IllegalArgumentException(ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
        }
        if(recipeRepository.findById(id).isPresent()) {
            recipe.setId(id);
            return recipeRepository.save(recipe);
        }
        return null;
    }

    @Override
    public Recipe delete(String id) {
        if(id == null  || id.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
        }
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()) {
            recipeRepository.delete(recipe.get());
            return recipe.get();
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
