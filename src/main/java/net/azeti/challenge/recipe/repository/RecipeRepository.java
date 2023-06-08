package net.azeti.challenge.recipe.repository;

import net.azeti.challenge.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query(value = "SELECT r FROM Recipe r WHERE r.username = :username")
    List<Recipe> findRecipesByUsername(@Param("username") String username);
}
