window.onload = loadRecipes;

function loadRecipes()
{
    let uri = "http://localhost:8080/recipes";
    let init = {
        method: "get",
        mode: "cors"
    };

    fetch(uri, init)
        .then(function(response) {
            console.log(response);
            return response.json();
        })
        .then(function(data) {
            updateDOM(data);
        })
}

function updateDOM(data)
{
    console.log(data);

    let list = document.getElementById("recipes");
    for (let i = 0; i < data.length; i++)
    {
        let recipe = data[i];
        let li = document.createElement("li");
        li.innerHTML = recipe.name;
        list.appendChild(li);
    }
}