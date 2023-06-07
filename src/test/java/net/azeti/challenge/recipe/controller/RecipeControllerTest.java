package net.azeti.challenge.recipe.controller;


import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.service.RecipeService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    @DisplayName("Return status created when create new recipe.")
    public void returnStatusCreatedWhenCreateNewRecipe() throws Exception {
        RecipeRequest request = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Recipe recipe = RecipeConverter.toRecipe(request);

        Recipe result = Recipe.builder()
                .id(1L)
                .description(request.getDescription())
                .title(request.getTitle())
                .instructions(request.getInstructions())
                .username(request.getUsername())
                .serving(request.getServing())
                .build();

        Mockito.when(recipeService.create(recipe)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RecipeConverter.toJsonString(request)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers.content().string(result.getId().toString()));
    }

    @Test
    @DisplayName("Return status ok when get recipe by id.")
    public void returnStatusOkWhenGetRecipeById() throws Exception {
        long id = 1L;

        Optional<Recipe> result = Optional.of(Recipe.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build());

        Mockito.when(recipeService.getById(id)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RecipeConverter.toJsonString(RecipeConverter.toRecipeResponse(result))));
    }

    @Test
    @DisplayName("Return status not found when get recipe by id is empty.")
    public void returnStatusNotFoundWhenGetRecipeByIdIsEmpty() throws Exception {
        long id = 1L;

        Optional<Recipe> result = Optional.of(Recipe.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build());

        Mockito.when(recipeService.getById(id)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
