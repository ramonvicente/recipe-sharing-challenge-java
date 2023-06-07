package net.azeti.challenge.recipe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponse {
    private long id;
    private String title;
    private String username;
    private String description;
    private String instructions;
    private int serving;
}
