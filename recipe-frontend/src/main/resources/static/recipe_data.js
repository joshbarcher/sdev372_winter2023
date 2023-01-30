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

}

