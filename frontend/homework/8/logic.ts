import { IRecipe } from "./util/recipe";

const fetchUrl: string = "https://dummyjson.com/recipes";
const searchUrl: string = "https://dummyjson.com/recipes/search";

export async function fetchRecipesFromAPI(): Promise<IRecipe[]> {
  try {
    const res: Response = await fetch(fetchUrl);
    const jsonRes: { recipes: any[] } = await res.json(); 
    return jsonRes.recipes.map(parseResponse);
  } catch (error) {
    console.error("Error fetching recipes:", error); 
    return [];
  }
}

export async function searchRecipes(query: string): Promise<IRecipe[]> {
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
