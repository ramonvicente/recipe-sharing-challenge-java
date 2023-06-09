package net.azeti.challenge.recipe.config;

import net.azeti.challenge.recipe.repository.IngredientRepository;
import net.azeti.challenge.recipe.repository.RecipeRepository;
import net.azeti.challenge.recipe.service.RecipeService;
import net.azeti.challenge.recipe.service.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeConfig {

    @Bean
    public RecipeService recipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        return new RecipeServiceImpl(recipeRepository, ingredientRepository);
    }
}
