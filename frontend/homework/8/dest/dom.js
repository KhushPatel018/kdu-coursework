"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var logic_1 = require("../logic");
function addNewRecipe(recipeData) {
  // Create the recipe container
  var recipeContainer = document.querySelector(".recipe-container");
  if (!recipeContainer) {
    console.error("Recipe container not found");
    return;
  }
  // Create the recipe wrapper
  var recipeWrapper = document.createElement("div");
  recipeWrapper.classList.add("recipe-wrap");
  // Create the recipe
  var recipe = document.createElement("div");
  recipe.classList.add("recipe");
  // Create the image element
  var image = document.createElement("img");
  image.setAttribute("src", recipeData.image);
  image.setAttribute("alt", "this is recipe");
  // Create the item name (recipe name)
  var itemName = document.createElement("h2");
  itemName.classList.add("item-name");
  itemName.innerText = recipeData.name;
  // Create the ingredients list
  var ingredients = document.createElement("div");
  ingredients.classList.add("ingredients");
  ingredients.innerText = recipeData.ingredients.join(", ");
  // Create the cuisin-diff wrapper
  var cuisinDiffWrapper = document.createElement("div");
  cuisinDiffWrapper.classList.add("cuisin-diff");
  // Create the cuisin element
  var cuisin = document.createElement("h2");
  cuisin.classList.add("cuisin");
  cuisin.innerText = recipeData.cuisine;
  // Create the difficulty element
  var diff = document.createElement("h2");
  diff.classList.add("diff");
  diff.innerText = recipeData.difficulty;
  // Create the time-cal wrapper
  var timeCalWrapper = document.createElement("div");
  timeCalWrapper.classList.add("time-cal");
  // Create the time element
  var time = document.createElement("div");
  time.classList.add("time");
  time.innerText = recipeData.timeTaken.toString(); // Convert to string
  // Create the calories element
  var cal = document.createElement("div");
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
document.addEventListener("DOMContentLoaded", function () {
  (0, logic_1.fetchRecipesFromAPI)()
    .then(function (recipes) {
      return recipes.forEach(function (recipe) {
        return addNewRecipe(recipe);
      });
    })
    .catch(function (err) {
      return console.log(err);
    });
});
var searchButton = document.getElementById("search");
var searchInput = document.getElementById("search-input");
searchButton === null || searchButton === void 0
  ? void 0
  : searchButton.addEventListener("click", function () {
      var query =
        searchInput === null || searchInput === void 0
          ? void 0
          : searchInput.innerText;
      if (typeof query === undefined) return false;
      else if (typeof query === "string") {
        (0, logic_1.searchRecipes)(query)
          .then(function (recipes) {
            return recipes.forEach(function (recipe) {
              return addNewRecipe(recipe);
            });
          })
          .catch(function (err) {
            return console.log(err);
          });
      }
    });
