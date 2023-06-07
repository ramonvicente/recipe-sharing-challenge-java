package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Recipe create(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.empty();
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
