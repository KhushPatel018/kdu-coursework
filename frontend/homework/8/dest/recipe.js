"use strict";
var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.display = exports.recipeToString = void 0;
var recipeToString = function (recipe) {
    var id = recipe.id, image = recipe.image, name = recipe.name, rating = recipe.rating, cuisine = recipe.cuisine, ingredients = recipe.ingredients, difficulty = recipe.difficulty, timeTaken = recipe.timeTaken, caloriesCount = recipe.caloriesCount;
    return "Recipe:\n      ID: ".concat(id, "\n      Image: ").concat(image, "\n      Name: ").concat(name, "\n      Rating: ").concat(rating, "\n      Cuisine: ").concat(cuisine, "\n      Ingredients: ").concat(ingredients.join(", "), "\n      Difficulty: ").concat(difficulty, "\n      Time Taken: ").concat(timeTaken, " minutes\n      Calories Count: ").concat(caloriesCount);
};
exports.recipeToString = recipeToString;
function display(recipe) {
    var recipeWithToString = __assign(__assign({}, recipe), { toString: function () { return (0, exports.recipeToString)(recipe); } });
    console.log(recipeWithToString.toString() + "\n");
}
exports.display = display;
