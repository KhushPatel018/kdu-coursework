interface IRecipe {
  id: number;
  image: string;
  name: string;
  rating: number;
  cuisine: string;
  ingredients: string[];
  difficulty: string;
  timeTaken: number;
  caloriesCount: number;
}

interface IRecipeWithToString extends IRecipe {
  toString: () => string;
}

const recipeToString = (recipe: IRecipeWithToString): string => {
  const {
    id,
    image,
    name,
    rating,
    cuisine,
    ingredients,
    difficulty,
    timeTaken,
    caloriesCount,
  } = recipe;

  return `Recipe:
        ID: ${id}
        Image: ${image}
        Name: ${name}
        Rating: ${rating}
        Cuisine: ${cuisine}
        Ingredients: ${ingredients.join(", ")}
        Difficulty: ${difficulty}
        Time Taken: ${timeTaken} minutes
        Calories Count: ${caloriesCount}`;
};

function display(recipe: IRecipe) {
  const recipeWithToString: IRecipeWithToString = {
    ...recipe,
    toString: () => recipeToString(recipe),
  };

  console.log(recipeWithToString.toString() + "\n");
}

const fetchUrl: string = "https://dummyjson.com/recipes";
const searchUrl: string = "https://dummyjson.com/recipes/search";

async function fetchRecipesFromAPI(): Promise<IRecipe[]> {
  try {
    const res: Response = await fetch(fetchUrl);
    const jsonRes: { recipes: any[] } = await res.json();
    return jsonRes.recipes.map(parseResponse);
  } catch (error) {
    console.error("Error fetching recipes:", error);
    return [];
  }
}

async function searchRecipes(query: string): Promise<IRecipe[]> {
  try {
    const res: Response = await fetch(`${searchUrl}?q=${query}`);
    const jsonRes: { recipes: any[] } = await res.json();
    return jsonRes.recipes.map(parseResponse);
  } catch (error) {
    console.error("Error searching recipes:", error);
    return [];
  }
}

function parseResponse(response: any): IRecipe {
  return {
    id: response.id,
    image: response.image,
    name: response.name,
    rating: response.rating,
    cuisine: response.cuisine,
    ingredients: response.ingredients,
    difficulty: response.difficulty,
    timeTaken: response.prepTimeMinutes + response.cookTimeMinutes,
    caloriesCount: response.servings * response.caloriesPerServing,
  };
}

const searchQuery: string = "pizza";

async function printAllRecipes(): Promise<void> {
  try {
    console.log("All recipes:\n");
    const recipes = await fetchRecipesFromAPI();
    recipes.forEach((recipe) => display(recipe));
  } catch (error) {
    console.error("Error fetching recipes:", error);
  }
}

async function printAllSearchedRecipes(searchQuery: string): Promise<void> {
  try {
    console.log(`Searched recipes for query "${searchQuery}":\n`);
    const recipes = await searchRecipes(searchQuery);
    recipes.forEach((recipe) => display(recipe));
  } catch (error) {
    console.error("Error searching recipes:", error);
  }
}

async function main(): Promise<void> {
  await printAllRecipes();
  await printAllSearchedRecipes(searchQuery);
}

// main();

function addNewRecipe(recipeData: IRecipe): void {
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
  ingredients.classList.add("ingerdients");
  ingredients.innerText = recipeData.ingredients.join(", ");

  // Create the cuisine-diff wrapper
  const cuisineDiffWrapper = document.createElement("div");
  cuisineDiffWrapper.classList.add("cuisine-diff");

  // Create the cuisine element
  const cuisine = document.createElement("div");
  cuisine.classList.add("cuisine");
  cuisine.innerText = recipeData.cuisine;

  // Create the difficulty element
  const diff = document.createElement("div");
  diff.classList.add("diff");
  diff.innerText = recipeData.difficulty;

  // Create the time-cal wrapper
  const timeCalWrapper = document.createElement("div");
  timeCalWrapper.classList.add("time-cal");

  // Create the time element
  const time = document.createElement("div");
  time.classList.add("time");
  time.innerText = recipeData.timeTaken.toString();

  // Create the calories element
  const cal = document.createElement("div");
  cal.classList.add("cal");
  cal.innerText = recipeData.caloriesCount.toString();

  const rating = document.createElement("div");
  rating.classList.add("rating");
  rating.innerText = recipeData.rating.toString();
  // Append elements to their respective parents
  recipe.appendChild(image);
  recipe.appendChild(itemName);
  recipe.appendChild(ingredients);

  cuisineDiffWrapper.appendChild(cuisine);
  cuisineDiffWrapper.appendChild(diff);
  recipe.appendChild(cuisineDiffWrapper);

  timeCalWrapper.appendChild(time);
  timeCalWrapper.appendChild(cal);
  recipe.appendChild(timeCalWrapper);

  recipeWrapper.appendChild(recipe);
  recipeWrapper.appendChild(rating);

  // Append the recipe wrapper to the recipe container
  recipeContainer.appendChild(recipeWrapper);
}

document.addEventListener("DOMContentLoaded", async () => {
  try {
    const recipes = await fetchRecipesFromAPI();
    recipes.forEach((recipe) => addNewRecipe(recipe));
  } catch (err) {
    console.error("Error fetching recipes:", err);
  }
});

const searchButton = document.getElementById("search");
const searchInput = document.getElementById("search-input");

searchButton?.addEventListener("click", async () => {
  const allRecipeCards = document.querySelectorAll<HTMLElement>(".recipe-wrap");
  console.log(allRecipeCards);
  allRecipeCards?.forEach((recipeCard) => {
    console.log("recipe card removed : ", recipeCard);
    recipeCard.remove();
  });
  const query = searchInput?.value;
  if (!query) return;
  try {
    const recipes = await searchRecipes(query);
    console.log("reached add recipe");
    recipes.forEach((recipe) => addNewRecipe(recipe));
  } catch (err) {
    console.error("Error searching recipes:", err);
  }
});
