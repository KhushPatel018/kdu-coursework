export interface IRecipe {
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

export interface IRecipeWithToString extends IRecipe {
  toString: () => string;
}

export const recipeToString = (recipe: IRecipeWithToString): string => {
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

export function display(recipe: IRecipe) {
  const recipeWithToString: IRecipeWithToString = {
    ...recipe,
    toString: () => recipeToString(recipe),
  };

  console.log(recipeWithToString.toString() + "\n");
}
