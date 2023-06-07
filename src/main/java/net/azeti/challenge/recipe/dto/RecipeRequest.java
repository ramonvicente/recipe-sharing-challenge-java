package net.azeti.challenge.recipe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RecipeRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String username;
    private String description;
    @NotBlank
    private String instructions;
    private int serving;
}
