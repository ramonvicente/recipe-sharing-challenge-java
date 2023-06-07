package net.azeti.challenge.recipe.repository;

import net.azeti.challenge.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
