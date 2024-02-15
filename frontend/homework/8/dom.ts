    import { IRecipe } from "./util/recipe";
    import { fetchRecipesFromAPI, searchRecipes } from "./logic";

    function addNewRecipe(recipeData: IRecipe) {
    // Create the recipe container
    const recipeContainer =
        document.querySelector<HTMLElement>(".recipe-container");

    if (!recipeContainer) {
        console.error("Recipe container not found");
        return;
    }

    // Create the recipe wrapper
    const recipeWrapper = document.createElement("div");
    recipeWrapper.classList.add("recipe-wrap");

    // Create the recipe
    const recipe = document.createElement("div");
    recipe.classList.add("recipe");

    // Create the image element
    const image = document.createElement("img");
    image.setAttribute("src", recipeData.image);
    image.setAttribute("alt", "this is recipe");

    // Create the item name (recipe name)
    const itemName = document.createElement("h2");
    itemName.classList.add("item-name");
    itemName.innerText = recipeData.name;

    // Create the ingredients list
    const ingredients = document.createElement("div");
    ingredients.classList.add("ingredients");
    ingredients.innerText = recipeData.ingredients.join(", ");

    // Create the cuisin-diff wrapper
    const cuisinDiffWrapper = document.createElement("div");
    cuisinDiffWrapper.classList.add("cuisin-diff");

    // Create the cuisin element
    const cuisin = document.createElement("h2");
    cuisin.classList.add("cuisin");
    cuisin.innerText = recipeData.cuisine;

    // Create the difficulty element
    const diff = document.createElement("h2");
    diff.classList.add("diff");
    diff.innerText = recipeData.difficulty;

    // Create the time-cal wrapper
    const timeCalWrapper = document.createElement("div");
    timeCalWrapper.classList.add("time-cal");

    // Create the time element
    const time = document.createElement("div");
    time.classList.add("time");
    time.innerText = recipeData.timeTaken.toString(); // Convert to string

    // Create the calories element
    const cal = document.createElement("div");
    cal.classList.add("cal");
    cal.innerText = recipeData.caloriesCount.toString(); // Convert to string

    // Append elements to their respective parents
    recipe.appendChild(image);
    recipe.appendChild(itemName);
    recipe.appendChild(ingredients);

    cuisinDiffWrapper.appendChild(cuisin);
    cuisinDiffWrapper.appendChild(diff);
    recipe.appendChild(cuisinDiffWrapper);

    timeCalWrapper.appendChild(time);
    timeCalWrapper.appendChild(cal);
    recipe.appendChild(timeCalWrapper);

    recipeWrapper.appendChild(recipe);

    // Append the recipe wrapper to the recipe container
    recipeContainer.appendChild(recipeWrapper);
    }

    document.addEventListener("DOMContentLoaded", () => {
    fetchRecipesFromAPI()
        .then((recipes) => recipes.forEach((recipe) => addNewRecipe(recipe)))
        .catch((err) => console.log(err));
    });

    const searchButton = document.getElementById("search");
    const searchInput = document.getElementById("search-input");

    searchButton?.addEventListener("click", () => {
    const query = searchInput?.innerText;
    if (typeof query === undefined) return false;
    else if (typeof query === "string") {
        searchRecipes(query)
        .then((recipes) => recipes.forEach((recipe) => addNewRecipe(recipe)))
        .catch((err) => console.log(err));
    }
    });
