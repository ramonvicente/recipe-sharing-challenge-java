package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
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

import java.util.List;
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
        RecipeRequest request = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Recipe savedRecipe = Recipe.builder()
                .id("b3a09e00-0630-11ee-be56-0242ac120002")
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Mockito.when(recipeRepository.saveAndFlush(Mockito.any(Recipe.class))).thenReturn(savedRecipe);

        //when
        RecipeIdResponse actualRecipe = recipeService.create(request);

        //then
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(savedRecipe.getId());
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
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";
        Optional<Recipe> returnRecipe = Optional.ofNullable(Recipe.builder().id(id).build());
        Mockito.when(recipeRepository.findById(id)).thenReturn(returnRecipe);

        //when
        RecipeResponse actualRecipe = recipeService.getById(id);

        //then
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should update recipe given existing id.")
    public void shouldUpdateRecipeGivenExistingId() {
        //given
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";
        RecipeRequest recipeRequest = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Recipe recipe = Recipe.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();

        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));
        Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

        //when
        RecipeResponse actualRecipe = recipeService.update(id, recipeRequest);

        //then
        assertThat(recipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(recipe.getId());
    }

    @Test
    @DisplayName("Should return null when update recipe given non existing id.")
    public void shouldReturnNullWhenUpdateRecipeGivenNonExistingId() {
        //given
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";
        RecipeRequest recipe = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(1)
                .build();
        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        //when
        RecipeResponse actualUpdate = recipeService.update(id, recipe);

        //then
        assertThat(actualUpdate).isNull();
    }

    @Test
    @DisplayName("Should throw exception when update recipe given id null.")
    public void shouldThrowExceptionWhenUpdateRecipeGivenIdNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            RecipeRequest request = RecipeRequest.builder()
                    .title("title")
                    .instructions("instructions")
                    .description("description")
                    .username("username")
                    .serving(1)
                    .build();
            recipeService.update(null, request);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
    }

    @Test
    @DisplayName("Should throw exception when update recipe given recipe null.")
    public void shouldThrowExceptionWhenUpdateRecipeGivenRecipeNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.update("recipe-id", null);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MEESAGE_RECIPE_SHOULD_NOT_BE_NULL);
    }

    @Test
    @DisplayName("Should delete recipe given existing id.")
    public void shouldDeleteRecipeGivenExistingId() {
        //given
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";
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
        RecipeResponse actualRecipe = recipeService.delete(id);

        //then
        Mockito.verify(recipeRepository, Mockito.times(1)).delete(recipe);
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should return null when delete recipe given non existing id.")
    public void shouldReturnNullWhenDeleteRecipeGivenNonExistingId() {
        //given
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";
        Mockito.when(recipeRepository.findById(id)).thenReturn(Optional.empty());

        //when
        RecipeResponse actualRecipe = recipeService.delete(id);

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
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_ID_SHOULD_NOT_BE_NULL_OR_BLANK);
    }

    @Test
    @DisplayName("Should return recipes when getByUser given existing username.")
    public void shouldReturnRecipesWhenGetByUserGivenExistingUsername() {
        //given
        String username = "username";
        Recipe recipe = Recipe.builder()
                .id("b3a09e00-0630-11ee-be56-0242ac120002")
                .title("title")
                .instructions("instructions")
                .description("description")
                .username(username)
                .serving(1)
                .build();
        Mockito.when(recipeRepository.findRecipesByUsername(username)).thenReturn(List.of(recipe));

        //when
        List<Recipe> actualRecipe = recipeService.getByUser(username);

        //then
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe).isNotEmpty();
        assertThat(actualRecipe.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should not return recipes when getByUser given non existing username.")
    public void shouldNotReturnRecipesWhenGetByUserGivenNonExistingUsername() {
        //given
        String username = "username";

        Mockito.when(recipeRepository.findRecipesByUsername(username)).thenReturn(List.of());

        //when
        List<Recipe> actualRecipe = recipeService.getByUser(username);

        //then
        assertThat(actualRecipe).isNotNull();
        assertThat(actualRecipe).isEmpty();
    }

    @Test
    @DisplayName("Should throw exception when getByUser given username null.")
    public void shouldThrowExceptionWhengetByUserGivenUsernameNull() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.getByUser(null);
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK);
    }

    @Test
    @DisplayName("Should throw exception when getByUser given username empty.")
    public void shouldThrowExceptionWhengetByUserGivenUsernameEmpty() {
        //given
        IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
            recipeService.getByUser("");
        });

        //then
        assertThat(exception).hasMessage(RecipeServiceImpl.ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK);
    }
}
