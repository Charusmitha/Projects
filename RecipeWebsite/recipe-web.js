const recipeWeb = {
    pageWrap: function(content) {
        return `
            <!DOCTYPE html>
            <html>
            <head>
                <link rel="stylesheet" href="/recipe.css"/>
                <title>Recipe</title>
            </head>
            <body>
                <div id="recipe">
                    ${content}
                </div>
                <div class="status"></div>
            <script src="/recipe.js"></script>
            </body>
            </html>
        `;
    },
    recipePage: function(recipe) {
        return this.pageWrap(`
            <div class="homePage">
                <div class="heading">
                    <h3>Welcome to AllRecipes</h3>
                </div>
                <div class="recipe-list">
                    ${this.getRecipeList(recipe)}
                </div>
                <div class="add-recipe">
                    ${this.addRecipe(recipe)}
                </div>
                <div class="homePageStatus"></div>
            </div>
        `);
    },
    formatRecipe: function(recipe) {
        return `
          <li>
            <div class="individualRecipe">
                <form action="/displayRecipe" method="GET">
                    <input type="hidden" name="recipeName" value="${recipe}"/>
                    <button id="targetRecipe" type="submit">${recipe}</button>
                </form>
            </div>
          </li>
        `;
    },
    getRecipeList: function(recipe) {
        return `
            <ul class="recipes">
                ${ Object.values(recipe.recipes).map( this.formatRecipe ).join('') }
            </ul>
        `;
    },
    addRecipe: function(recipe) {
        return `
            <form action="/addNewRecipe" method="GET">
                <button id="addRecipe" type="submit">Add New Recipe</button>
            </form>
        `;
    },
    addNewRecipePage: function() {
        return this.pageWrap(`
          <div class="addNewRecipe">
            <div class="newRecipe">
                    <h3>Add new recipe</h3>
            </div>
            <div class="getInfo">
                <form action="/addNewRecipe" method="POST">
                    Title: <input class="name" type="text" name="title" required />
                    Ingredients: <input class="ingredients" type="text" name="ingredients" required />
                    Instructions: <input class="instructions" type="text" name="instructions" required />
                    <button id="confirm" type="submit">Confirm</button> 
                </form>
            </div>
            <div class="home">
                <form action="/", method="GET">
                    <button id="returnHomeFromNewRecipePage" type="submit">Home</button> 
                </form>
            </div>
            <div class="newRecipePageStatus"></div>
          </div>
        `);
    },
    recipeDetailPage: function(result) {
        return this.pageWrap(`
            <div class="recipeInfo">
                <div class="recipeDetail">
                    <p class="title">${result.title}</p>
                    <p class="addIngredients">Ingredients: ${result.ingredients}</p>
                    <p class="addInstructions">Instructions: ${result.instructions}</p>
                </div>
                <div class="homeButton">
                    <form action="/", method="GET">
                        <button id="returnHomeFromDetailPage" type="submit">Home</button> 
                    </form>
                </div>
                <div class="recipeDetailPageStatus"></div>
            </div>
        `);
    },
    

};
module.exports = recipeWeb;
