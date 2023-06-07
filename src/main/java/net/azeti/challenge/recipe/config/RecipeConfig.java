package net.azeti.challenge.recipe.config;

import net.azeti.challenge.recipe.controller.RecipeController;
import net.azeti.challenge.recipe.repository.RecipeRepository;
import net.azeti.challenge.recipe.service.RecipeService;
import net.azeti.challenge.recipe.service.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeConfig {

    @Bean
    public RecipeService recipeService(RecipeRepository recipeRepository) {
        return new RecipeServiceImpl(recipeRepository);
    }

    public RecipeController recipeController(RecipeService recipeService) {
        return new RecipeController(recipeService);
    }
}
