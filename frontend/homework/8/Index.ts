import { display } from "./util/recipe";
import { fetchRecipesFromAPI, searchRecipes } from "./logic";

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

main();
