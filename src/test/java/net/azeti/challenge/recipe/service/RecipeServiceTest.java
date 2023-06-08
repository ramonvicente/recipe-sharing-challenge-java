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

    @Test
    @DisplayName("Should update recipe given existing id.")
    public void shouldUpdateRecipeGivenExistingId() {
        //given
        long id = 1L;
        Recipe recipe = Recipe.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Recipe recipeResponse = Recipe.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();

        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.of(recipeResponse));
        Mockito.when(recipeRepository.save(recipe)).thenReturn(recipeResponse);

        //when
        Recipe actualRecipe = recipeService.update(id, recipe);

        //then
        assertThat(recipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(recipe.getId());
    }

    @Test
    @DisplayName("Should return null when update recipe given non existing id.")
    public void shouldReturnNullWhenUpdateRecipeGivenNonExistingId() {
        //given
        long id = 1L;
        Recipe recipe = Recipe.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Recipe actualUpdate = recipeService.update(id, recipe);

        //then
        assertThat(actualUpdate).isNull();
    }

    @Test
    @DisplayName("Should throw exception when update recipe given id null.")
    public void shouldThrowExceptionWhenUpdateRecipeGivenIdNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.update(null, new Recipe());
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL);
    }

    @Test
    @DisplayName("Should throw exception when update recipe given recipe null.")
    public void shouldThrowExceptionWhenUpdateRecipeGivenRecipeNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.update(1L, null);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
    }

    @Test
    @DisplayName("Should delete recipe given existing id.")
    public void shouldDeleteRecipeGivenExistingId() {
        //given
        long id = 1L;
        Recipe recipe = Recipe.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        //when
        Recipe actualRecipe = recipeService.delete(id);

        //then
        Mockito.verify(recipeRepository, Mockito.times(1)).delete(recipe);
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should return null when delete recipe given non existing id.")
    public void shouldReturnNullWhenDeleteRecipeGivenNonExistingId() {
        //given
        long id = 1L;
        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        //when
        Recipe actualRecipe = recipeService.delete(id);

        //then
        assertThat(actualRecipe).isNull();
    }

    @Test
    @DisplayName("Should throw exception when delete recipe given id null.")
    public void shouldThrowExceptionWhenDeleteRecipeGivenIdNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.delete(null);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL);
    }
}
