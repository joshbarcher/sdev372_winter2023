package edu.greenriver.sdev.cookingapp.controllers;

import edu.greenriver.sdev.cookingapp.model.Recipe;
import edu.greenriver.sdev.cookingapp.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("recipes") //all paths start with "recipes"
public class RecipeApi
{
    private RecipeService service;

    public RecipeApi(RecipeService service)
    {
        this.service = service;
        System.out.println("Service loaded");
    }

    //http://localhost:8080/recipes
    @GetMapping("")
    public ResponseEntity<List<Recipe>> allRecipes()
    {
        return new ResponseEntity<>(service.allRecipes(), HttpStatus.OK);
    }

    //http://localhost:8080/recipes/{recipeName}
    @GetMapping("{recipeName}")
    public ResponseEntity<Recipe> recipeByName(@PathVariable String recipeName)
    {
        //if the recipe is missing
        if (service.findRecipeByName(recipeName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.findRecipeByName(recipeName), HttpStatus.OK);
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
        if (!service.isValidRecipe(recipe))
        {
            //no response body, status code 400
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //response body is saved recipe, status code 201
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
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe updatedRecipe)
    {
        //if not found
        if (service.findRecipeByName(updatedRecipe.getName()) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //if invalid data
        else if (!service.isValidRecipe(updatedRecipe))
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateRecipe(updatedRecipe), HttpStatus.OK);
    }

    //http://localhost:8080/recipes/Tacos
    @DeleteMapping("{recipeName}")
    public void deleteRecipe(@PathVariable String recipeName)
    {
        service.deleteRecipe(recipeName);
    }
}
