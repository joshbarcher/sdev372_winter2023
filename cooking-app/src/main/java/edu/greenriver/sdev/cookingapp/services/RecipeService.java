package edu.greenriver.sdev.cookingapp.services;

import edu.greenriver.sdev.cookingapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService
{
    private List<Recipe> recipes = new ArrayList<>(List.of(
        Recipe.builder()
            .vegan(false)
            .cookTime(45)
            .name("Yellow Cake")
            .ingredients(List.of(
                "butter", "oil", "flour", "sugar"
            ))
            .servings(15)
            .build(),
        Recipe.builder()
            .vegan(false)
            .cookTime(15)
            .name("Tacos")
            .ingredients(List.of(
                "ground beef", "cheddar cheese", "sour cream"
            ))
            .servings(6)
            .build(),
        Recipe.builder()
            .vegan(true)
            .cookTime(20)
            .name("Nigerian Rice")
            .ingredients(List.of(
                "bouillon cube", "rice", "carrots", "peas"
            ))
            .servings(20)
            .build()
    ));

    //GET requests (read)

    public List<Recipe> allRecipes()
    {
        return recipes;
    }

    public Recipe findRecipeByName(String recipeName)
    {
        return recipes.stream()
            .filter(rec -> rec.getName().equalsIgnoreCase(recipeName))
            .findFirst()
            .orElse(null);
    }

    //POST requests (create)
    public Recipe addRecipe(Recipe newRecipe)
    {
        //make changes to the recipe later...

        recipes.add(newRecipe);
        return newRecipe;
    }

    //PUT requests (update)
    public void updateRecipe(Recipe updatedRecipe)
    {
        Recipe found = findRecipeByName(updatedRecipe.getName());
        if (found != null)
        {
            found.setIngredients(updatedRecipe.getIngredients());
            found.setVegan(updatedRecipe.isVegan());
            found.setCookTime(updatedRecipe.getCookTime());
            found.setServings(updatedRecipe.getServings());
        }
    }

    //DELETE requests (delete)
    public void deleteRecipe(String recipeName)
    {
        //filter out just the matching recipe name
        recipes = new ArrayList<>(
            recipes.stream()
                .filter(rec -> !rec.getName().equalsIgnoreCase(recipeName))
                .toList()
        );

        /*Recipe found = findRecipeByName(recipeName);
        recipes.remove(found);*/
    }

    public List<Recipe> filterByVegan(boolean vegan)
    {
        return recipes.stream()
            .filter(recipe -> recipe.isVegan() == vegan)
            .toList();
    }
}
