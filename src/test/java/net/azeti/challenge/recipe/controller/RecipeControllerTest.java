package net.azeti.challenge.recipe.controller;


import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
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

import java.util.List;

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
                .id("recipe-id")
                .description(request.getDescription())
                .title(request.getTitle())
                .instructions(request.getInstructions())
                .username(request.getUsername())
                .serving(request.getServing())
                .build();

        RecipeIdResponse responseId = RecipeConverter.toRecipeIdResponse(result);
        Mockito.when(recipeService.create(request)).thenReturn(responseId);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RecipeConverter.toJsonString(request)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated())
                .andExpect(MockMvcResultMatchers.content().string(RecipeConverter.toJsonString(responseId)));
    }

    @Test
    @DisplayName("Return bad request when create recipe has invalid field.")
    public void returnBadRequestWhenCreateRecipeHasInvalidField() throws Exception {
        RecipeRequest requestWithouUsername = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .serving(2)
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RecipeConverter.toJsonString(requestWithouUsername)))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest());
    }

    @Test
    @DisplayName("Return status ok when get recipe by id.")
    public void returnStatusOkWhenGetRecipeById() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        RecipeResponse result = RecipeResponse.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Mockito.when(recipeService.getById(id)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RecipeConverter.toJsonString(result)));
    }

    @Test
    @DisplayName("Return status not found when get recipe by id is empty.")
    public void returnStatusNotFoundWhenGetRecipeByIdIsEmpty() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        RecipeResponse result = RecipeResponse.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Mockito.when(recipeService.getById(id)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Return status ok when update existing recipe.")
    public void returnStatusOkWhenUpdateExistingRecipe() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        RecipeRequest request = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();
        RecipeResponse result = RecipeResponse.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Mockito.when(recipeService.update(id, request)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/recipes/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RecipeConverter.toJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RecipeConverter.toJsonString(result)));
    }

    @Test
    @DisplayName("Return status not found when update non existing recipe.")
    public void returnStatusNotFoundWhenUpdateNonExistingRecipe() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        RecipeRequest request = RecipeRequest.builder()
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Mockito.when(recipeService.update(id,request)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/recipes/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(RecipeConverter.toJsonString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Return status ok when delete existing recipe.")
    public void returnStatusOkWhenDeleteExistingRecipe() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        RecipeResponse response = RecipeResponse.builder()
                .id(id)
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();

        Mockito.when(recipeService.delete(id)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Return status not found when delete non existing recipe.")
    public void returnStatusNotFoundWhenDeleteNonExistingRecipe() throws Exception {
        String id = "b3a09e00-0630-11ee-be56-0242ac120002";

        Mockito.when(recipeService.delete(id)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/recipes/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Return status ok when delete existing recipe.")
    public void returnStatusOkWhenGetRecipeListByUsername() throws Exception {
        String username = "username";

        Recipe recipe = Recipe.builder()
                .id("b3a09e00-0630-11ee-be56-0242ac120002")
                .title("title")
                .instructions("instructions")
                .description("description")
                .username("username")
                .serving(2)
                .build();
        List<RecipeResponse> response = List.of(RecipeConverter.toRecipeResponse(recipe));

        List<Recipe> recipes = List.of(recipe);

        Mockito.when(recipeService.getByUser(username)).thenReturn(recipes);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/recipes")
                        .param("username", username))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(RecipeConverter.toJsonString(response)));
    }
}
