package net.azeti.challenge.recipe.controller;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.converter.RecipeConverter;
import net.azeti.challenge.recipe.dto.RecipeRequest;
import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @PostMapping("recipes")
    public ResponseEntity<Long> saveRecipe(@Valid @RequestBody RecipeRequest request) {
        Recipe recipe = service.create(RecipeConverter.toRecipe(request));
        return new ResponseEntity<>(recipe.getId(), HttpStatus.CREATED);
    }
}
