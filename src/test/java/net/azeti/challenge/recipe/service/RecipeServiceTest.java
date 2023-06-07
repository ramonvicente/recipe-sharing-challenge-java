package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.repository.RecipeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    @DisplayName("Should create new recipe given valid recipe.")
    public void shouldCreateNewRecipeGivenValidRecipe() {
        //given
        Recipe recipe = Recipe.builder()
                .id(1L)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Mockito.when(recipeRepository.saveAndFlush(recipe)).thenReturn(recipe);

        //when
        Recipe actualRecipe = recipeService.create(recipe);

        //then
        Mockito.verify(recipeRepository, Mockito.times(1)).saveAndFlush(recipe);
        assertThat(recipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(recipe.getId());
    }

    @Test
    @DisplayName("Should throw exception when create new recipe given recipe null.")
    public void shouldThrowExceptionWhenCreateNewRecipeGivenRecipeNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.create(null);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
    }

    @Test
    @DisplayName("Should return recipe when getById given valid id.")
    public void shouldReturnRecipeWhenGetByIdGivenValidId() {
        //given
        long id = 1L;
        Optional<Recipe> returnRecipe = Optional.ofNullable(Recipe.builder().id(id).build());
        Mockito.when(recipeRepository.findById(id)).thenReturn(returnRecipe);

        //when
        Recipe actualRecipe = recipeService.getById(id).get();

        //then
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(id);
    }
}
