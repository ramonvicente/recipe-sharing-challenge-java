package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    public static final String ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL = "recipe should not be null.";
    public static final String ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL = "id should not be null";

    private final RecipeRepository recipeRepository;

    @Override
    public Recipe create(Recipe recipe) {
        if(recipe == null) {
            throw new IllegalArgumentException(ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
        }
        return recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL);
        }
        return recipeRepository.findById(id);
    }

    @Override
    public Recipe update(Long id, Recipe recipe) {
        return null;
    }

    @Override
    public Recipe delete(Long id) {
        return null;
    }

    @Override
    public List<Recipe> getByUser(String username) {
        return null;
    }
}
