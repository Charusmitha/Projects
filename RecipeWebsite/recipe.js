const recipes = {
    "Fruit Smoothie" : "Fruit Smoothie",
    "Sesame Potato Chips" : "Sesame Potato Chips",
};

const recipeDetail = [
  {
    title: "Fruit Smoothie",
    ingredients: "Vanilla yogurt, frozen strawberries, frozen banana, orange juice, sugar",
    instructions: "Puree 1 cup vanilla yogurt, 1 cup frozen strawberries, 1 frozen banana and 1/4 cup orange juice in a blender until smooth.",
  },
  {
    title: "Sesame Potato Chips",
    ingredients: "Potatoes, olive oil, salt, sesame seeds",
    instructions: "Preheat 2 baking sheets in a 425 degrees F oven. Toss thinly sliced russet potatoes with olive oil and salt. Spread on the hot baking sheets, sprinkle with sesame seeds and bake 10 minutes.",
  }
];

function addRecipes({ title }) {
  recipes[title] = title;
}

function addRecipeDetail({ title, ingredients, instructions }) {
  recipeDetail.push({ title, ingredients, instructions });
}

const recipe = {
  recipes,
  recipeDetail,
  addRecipes,
  addRecipeDetail,
};

module.exports = recipe;