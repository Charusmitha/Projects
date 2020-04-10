const express = require('express');
const app = express();

const PORT = 3000;

const recipe = require('./recipe');
const recipeWeb = require('./recipe-web');

app.use(express.static('./public'));

app.get('/recipes/', (req,res, next) => {
    res.json(Object.keys(recipe.recipes));
});

app.get('/recipeDetail/', (req,res, next) => {
    res.json(recipe.recipeDetail);
});

app.post('/newRecipe/', express.json(), (req, res) => {
	const title = req.body;
	recipe.addRecipes( title );
	res.sendStatus(200);
});

app.post('/newRecipeDetail/', express.json(), (req, res) => {
	const { title, ingredients, instructions } = req.body;
	recipe.addRecipeDetail({ title: title, ingredients: ingredients, instructions: instructions });
	res.sendStatus(200);
});

app.get('/', (req, res) => {	
 	res.send(recipeWeb.recipePage(recipe));
});

app.get('/addNewRecipe', (req, res) => {
	res.send(recipeWeb.addNewRecipePage());
});

app.post('/addNewRecipe', express.urlencoded({ extended: false }), (req, res) => {
	const title = req.body.title;
	const ingredients = req.body.ingredients;
	const instructions = req.body.instructions;
	recipe.addRecipes({ title });
	recipe.addRecipeDetail({ title, ingredients, instructions });
	res.redirect(`/displayRecipe?recipeName=${title}`);
});

app.get('/displayRecipe', express.urlencoded({ extended: false }), (req, res) => {
	const result = recipe.recipeDetail.find(recipe => recipe.title === req.query.recipeName);
	res.send(recipeWeb.recipeDetailPage(result));
});

app.listen(PORT, () => console.log(`Listening on http://localhost:${PORT}`));