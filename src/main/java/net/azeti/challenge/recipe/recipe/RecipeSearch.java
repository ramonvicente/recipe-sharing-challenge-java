package net.azeti.challenge.recipe.recipe;

import net.azeti.challenge.recipe.model.Recipe;

import java.util.List;

public interface RecipeSearch {

    List<Recipe> recipesByUsername(String usernameValue);

    List<Recipe> recipesByTitle(String titleValue);
}
