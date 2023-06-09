package net.azeti.challenge.recipe.controller;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeIdResponse;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.dto.RecipeResponse;
import net.azeti.challenge.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @PostMapping(value = "recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeIdResponse> saveRecipe(@Valid @RequestBody RecipeRequest request) {
        RecipeIdResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "recipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
        RecipeResponse response =service.getById(id);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "recipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable String id, @Valid @RequestBody RecipeRequest request) {
        RecipeResponse response = service.update(id, request);
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "recipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable String id) {
        RecipeResponse response = RecipeConverter.toRecipeResponse(service.delete(id));
        if(response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeResponse>> getRecipesByName(@RequestParam String username) {
        List<RecipeResponse> response = service.getByUser(username).stream()
                .map(RecipeConverter::toRecipeResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
