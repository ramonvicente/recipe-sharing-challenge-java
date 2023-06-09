package net.azeti.challenge.recipe.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Builder
@Getter
@Setter
@Table(name = "ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="recipe_id", nullable = false)
    private Recipe recipe;
    @Column(name = "value", nullable = false)
    private double value;
    @Column(name = "unity", nullable = false)
    private String unity;
    @Column(name = "type", nullable = false)
    private String type;
}
