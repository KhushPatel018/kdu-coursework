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
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var _this = this;
var recipeToString = function (recipe) {
    var id = recipe.id, image = recipe.image, name = recipe.name, rating = recipe.rating, cuisine = recipe.cuisine, ingredients = recipe.ingredients, difficulty = recipe.difficulty, timeTaken = recipe.timeTaken, caloriesCount = recipe.caloriesCount;
    return "Recipe:\n        ID: ".concat(id, "\n        Image: ").concat(image, "\n        Name: ").concat(name, "\n        Rating: ").concat(rating, "\n        Cuisine: ").concat(cuisine, "\n        Ingredients: ").concat(ingredients.join(", "), "\n        Difficulty: ").concat(difficulty, "\n        Time Taken: ").concat(timeTaken, " minutes\n        Calories Count: ").concat(caloriesCount);
};
function display(recipe) {
    var recipeWithToString = __assign(__assign({}, recipe), { toString: function () { return recipeToString(recipe); } });
    console.log(recipeWithToString.toString() + "\n");
}
var fetchUrl = "https://dummyjson.com/recipes";
var searchUrl = "https://dummyjson.com/recipes/search";
function fetchRecipesFromAPI() {
    return __awaiter(this, void 0, void 0, function () {
        var res, jsonRes, error_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, fetch(fetchUrl)];
                case 1:
                    res = _a.sent();
                    return [4 /*yield*/, res.json()];
                case 2:
                    jsonRes = _a.sent();
                    return [2 /*return*/, jsonRes.recipes.map(parseResponse)];
                case 3:
                    error_1 = _a.sent();
                    console.error("Error fetching recipes:", error_1);
                    return [2 /*return*/, []];
                case 4: return [2 /*return*/];
            }
        });
    });
}
function searchRecipes(query) {
    return __awaiter(this, void 0, void 0, function () {
        var res, jsonRes, error_2;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 3, , 4]);
                    return [4 /*yield*/, fetch("".concat(searchUrl, "?q=").concat(query))];
                case 1:
                    res = _a.sent();
                    return [4 /*yield*/, res.json()];
                case 2:
                    jsonRes = _a.sent();
                    return [2 /*return*/, jsonRes.recipes.map(parseResponse)];
                case 3:
                    error_2 = _a.sent();
                    console.error("Error searching recipes:", error_2);
                    return [2 /*return*/, []];
                case 4: return [2 /*return*/];
            }
        });
    });
}
function parseResponse(response) {
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
var searchQuery = "pizza";
function printAllRecipes() {
    return __awaiter(this, void 0, void 0, function () {
        var recipes, error_3;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 2, , 3]);
                    console.log("All recipes:\n");
                    return [4 /*yield*/, fetchRecipesFromAPI()];
                case 1:
                    recipes = _a.sent();
                    recipes.forEach(function (recipe) { return display(recipe); });
                    return [3 /*break*/, 3];
                case 2:
                    error_3 = _a.sent();
                    console.error("Error fetching recipes:", error_3);
                    return [3 /*break*/, 3];
                case 3: return [2 /*return*/];
            }
        });
    });
}
function printAllSearchedRecipes(searchQuery) {
    return __awaiter(this, void 0, void 0, function () {
        var recipes, error_4;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 2, , 3]);
                    console.log("Searched recipes for query \"".concat(searchQuery, "\":\n"));
                    return [4 /*yield*/, searchRecipes(searchQuery)];
                case 1:
                    recipes = _a.sent();
                    recipes.forEach(function (recipe) { return display(recipe); });
                    return [3 /*break*/, 3];
                case 2:
                    error_4 = _a.sent();
                    console.error("Error searching recipes:", error_4);
                    return [3 /*break*/, 3];
                case 3: return [2 /*return*/];
            }
        });
    });
}
function main() {
    return __awaiter(this, void 0, void 0, function () {
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, printAllRecipes()];
                case 1:
                    _a.sent();
                    return [4 /*yield*/, printAllSearchedRecipes(searchQuery)];
                case 2:
                    _a.sent();
                    return [2 /*return*/];
            }
        });
    });
}
// main();
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
    ingredients.classList.add("ingerdients");
    ingredients.innerText = recipeData.ingredients.join(", ");
    // Create the cuisine-diff wrapper
    var cuisineDiffWrapper = document.createElement("div");
    cuisineDiffWrapper.classList.add("cuisine-diff");
    // Create the cuisine element
    var cuisine = document.createElement("div");
    cuisine.classList.add("cuisine");
    cuisine.innerText = recipeData.cuisine;
    // Create the difficulty element
    var diff = document.createElement("div");
    diff.classList.add("diff");
    diff.innerText = recipeData.difficulty;
    // Create the time-cal wrapper
    var timeCalWrapper = document.createElement("div");
    timeCalWrapper.classList.add("time-cal");
    // Create the time element
    var time = document.createElement("div");
    time.classList.add("time");
    time.innerText = recipeData.timeTaken.toString();
    // Create the calories element
    var cal = document.createElement("div");
    cal.classList.add("cal");
    cal.innerText = recipeData.caloriesCount.toString();
    var rating = document.createElement("div");
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
document.addEventListener("DOMContentLoaded", function () { return __awaiter(_this, void 0, void 0, function () {
    var recipes, err_1;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                _a.trys.push([0, 2, , 3]);
                return [4 /*yield*/, fetchRecipesFromAPI()];
            case 1:
                recipes = _a.sent();
                recipes.forEach(function (recipe) { return addNewRecipe(recipe); });
                return [3 /*break*/, 3];
            case 2:
                err_1 = _a.sent();
                console.error("Error fetching recipes:", err_1);
                return [3 /*break*/, 3];
            case 3: return [2 /*return*/];
        }
    });
}); });
var searchButton = document.getElementById("search");
var searchInput = document.getElementById("search-input");
searchButton === null || searchButton === void 0 ? void 0 : searchButton.addEventListener("click", function () { return __awaiter(_this, void 0, void 0, function () {
    var allRecipeCards, query, recipes, err_2;
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0:
                allRecipeCards = document.querySelectorAll(".recipe-wrap");
                console.log(allRecipeCards);
                allRecipeCards === null || allRecipeCards === void 0 ? void 0 : allRecipeCards.forEach(function (recipeCard) {
                    console.log("recipe card removed : ", recipeCard);
                    recipeCard.remove();
                });
                query = searchInput === null || searchInput === void 0 ? void 0 : searchInput.value;
                if (!query)
                    return [2 /*return*/];
                _a.label = 1;
            case 1:
                _a.trys.push([1, 3, , 4]);
                return [4 /*yield*/, searchRecipes(query)];
            case 2:
                recipes = _a.sent();
                console.log("reached add recipe");
                recipes.forEach(function (recipe) { return addNewRecipe(recipe); });
                return [3 /*break*/, 4];
            case 3:
                err_2 = _a.sent();
                console.error("Error searching recipes:", err_2);
                return [3 /*break*/, 4];
            case 4: return [2 /*return*/];
        }
    });
}); });
