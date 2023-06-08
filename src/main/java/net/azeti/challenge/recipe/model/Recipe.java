package net.azeti.challenge.recipe.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name="recipe_tb")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "description")
    private String description;
    @Column(name = "instructions", nullable = false)
    private String instructions;
    @Column(name = "servings")
    private int serving;
}
