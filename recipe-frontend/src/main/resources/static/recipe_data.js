//run this function on page load
window.onload = loadRecipes;

function loadRecipes()
{
    let uri = "http://localhost:8080/recipes";
    let params = {
        method: "get",
        mode: "cors"
    };

    fetch(uri, params)
        .then(function(response) {
            console.log(response);
            return response.json();
        })
        .then(function(json) {
            console.log(json);
            showRecipes(json);
        });

}

function showRecipes(recipes)
{
    //get our list
    let list = document.getElementById("recipes");

    //loop over all our recipes
    for (let i = 0; i < recipes.length; i++)
    {
        //add each recipe to the list
        let recipe = recipes[i];

        //create element using a function on document
        let li = document.createElement("li"); //<li></li>
        li.innerHTML = recipe.name; //<li>Tacos</li>

        //add spans
        let servingSpan = document.createElement("span");
        let timeSpan = document.createElement("span");

        servingSpan.className = "servings";
        timeSpan.className = "cook-time";

        servingSpan.innerHTML = recipe.servings + " servings";
        timeSpan.innerHTML = recipe.cookTime + " minutes";

        li.appendChild(servingSpan);
        li.appendChild(timeSpan);

        //attach the li to the DOM
        list.appendChild(li);

        //<li>Nigerian Rice <span>6 servings</span><span>30 minutes</span></li>
    }
}

