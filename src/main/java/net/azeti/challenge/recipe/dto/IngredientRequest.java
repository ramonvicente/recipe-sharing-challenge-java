package net.azeti.challenge.recipe.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class IngredientRequest {
    @NotNull
    private double value;
    @NotBlank
    private String unity;
    @NotBlank
    private String type;
}
