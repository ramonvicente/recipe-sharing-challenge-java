package net.azeti.challenge.recipe.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class IngredientResponse {
    private String id;
    private String unity;
    private String type;
    private double value;
}
