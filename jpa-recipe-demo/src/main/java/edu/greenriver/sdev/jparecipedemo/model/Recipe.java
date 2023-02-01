package edu.greenriver.sdev.jparecipedemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe
{
    //an auto-increment primary key (aka surrogate key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> ingredients;
    private double servings;
    private int cookTime;
    private boolean vegan;
}
