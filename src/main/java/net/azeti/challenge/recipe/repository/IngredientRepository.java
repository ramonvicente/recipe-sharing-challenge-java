package net.azeti.challenge.recipe.repository;

import net.azeti.challenge.recipe.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
