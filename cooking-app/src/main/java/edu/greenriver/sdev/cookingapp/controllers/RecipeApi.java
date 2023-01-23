package edu.greenriver.sdev.cookingapp.controllers;

import edu.greenriver.sdev.cookingapp.model.Recipe;
import edu.greenriver.sdev.cookingapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipes") //all paths start with "recipes"
public class RecipeApi
{
    private RecipeService service;

    public RecipeApi(RecipeService service)
    {
        this.service = service;
    }

    //http://localhost:8080/recipes
    @GetMapping("")
    public List<Recipe> allRecipes()
    {
        return service.allRecipes();
    }

    //http://localhost:8080/recipes/{recipeName}
    @GetMapping("{recipeName}")
    public Recipe recipeByName(@PathVariable String recipeName)
    {
        return service.findRecipeByName(recipeName);
    }

    //http://localhost:8080/recipes/filter?vegan=true
    @GetMapping("filter")
    public List<Recipe> filterByVegan(@RequestParam boolean vegan)
    {
        return service.filterByVegan(vegan);
    }

    //http://localhost:8080/recipes
    @PostMapping("")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe)
    {
        return new ResponseEntity<>(service.addRecipe(recipe), HttpStatus.CREATED);
    }

    @PostMapping("add")
    public void addRecipe(@RequestParam String name, @RequestParam double servings,
                          @RequestParam int cookTime, @RequestParam boolean vegan)
    {
        Recipe recipe = new Recipe(name, List.of(), servings, cookTime, vegan);
        service.addRecipe(recipe);
    }

    //http://localhost:8080/recipes
    @PutMapping("")
    public void updateRecipe(@RequestBody Recipe updatedRecipe)
    {
        service.updateRecipe(updatedRecipe);
    }

    //http://localhost:8080/recipes/Tacos
    @DeleteMapping("{recipeName}")
    public void deleteRecipe(@PathVariable String recipeName)
    {
        service.deleteRecipe(recipeName);
    }
}
