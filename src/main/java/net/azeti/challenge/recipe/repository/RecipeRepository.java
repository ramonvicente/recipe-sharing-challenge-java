package net.azeti.challenge.recipe.repository;

import net.azeti.challenge.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, String> {

    @Query(value = "SELECT r FROM Recipe r WHERE r.username = :username")
    List<Recipe> findRecipesByUsername(@Param("username") String username);
}
