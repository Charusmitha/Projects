( function IIFE() {
	'use strict';
	const status = document.querySelector('.status');

	const messageFor = {
    unknown: 'An error occured, please try again',
    network: 'The network failed, please try again',
  	};

  	const updateStatus = function( code ) {
    status.innerText = messageFor[code];
  	};

	const loadHomePage = () => {
    	return fetch('/recipes/')
		    .catch( err => Promise.reject({ error: 'network', err }) )
		    .then( response => {
		      if(response.ok) {
		        return response.json();
		      }
		      return Promise.reject({ error: 'unknown', err: response.statusText });
		    })
		    .then( recipes => {
		      const recipeNames = recipes;
		      document.getElementById('recipe').innerHTML = homePage(recipeNames);
		      status.innerHTML = '';
		    })
		    .catch( err => {
		      updateStatus(err.error);
		    });
  	};

  	const loadRecipeDetailPage = ( title ) => {
  		return fetch('/recipeDetail/')
		    .catch( err => Promise.reject({ error: 'network', err }) )
		    .then( response => {
		      if(response.ok) {
		        return response.json();
		      }
		      return Promise.reject({ error: 'unknown', err: response.statusText });
		    })
		    .then( detail => {
		      const recipeDetail = detail;
		      const result = recipeDetail.find(recipe => recipe.title === title);
		      document.getElementById('recipe').innerHTML = recipeDetailPage(result);
		      status.innerHTML = '';
		    })
		    .catch( err => {
		      updateStatus(err.error);
		    }); 
  	}

  	const updateRecipe = (title) => {
  		fetch('/newRecipe/', {
		      method: 'POST',
		      headers: new Headers({
		        'content-type': 'application/json'
		      }),
		      body: JSON.stringify({ title })
		    })
		    .catch( err => Promise.reject({ error: 'network', err }) )
		    .then( response => {
		      if(response.ok) {}
		      return Promise.reject({ error: 'unknown', err: response.statusText });
		    })
		    .catch( err => {
		      updateStatus(err.error);
		    });
  	}

  	const updateRecipeDetail = (title, ingredients, instructions) => {
  		fetch('/newRecipeDetail/', {
		      method: 'POST',
		      headers: new Headers({
		        'content-type': 'application/json'
		      }),
		      body: JSON.stringify({ title, ingredients, instructions })
		    })
		    .catch( err => Promise.reject({ error: 'network', err }) )
		    .then( response => {
		      if(response.ok) {}
		      return Promise.reject({ error: 'unknown', err: response.statusText });
		    })
		    .catch( err => {
		      updateStatus(err.error);
		    });
  	}

  	const homePage = function( recipeNames ) {
	    return `<div class="homePage">` +
	    			`<div class="heading">` +
	    				`<h3>Welcome to AllRecipes</h3>` +
	   			 	`</div>` +
	    			`<div class="recipe-list">` +
	    				`<ul class="recipes">` +
						    Object.values(recipeNames).map( recipe => 
						    	`<li>
						            <div class="individualRecipe">
						                <form>
						                    <input type="hidden" name="recipeName" value="${recipe}"/>
						                    <button id="targetRecipe" type="submit" class="recipeTitle">${recipe}</button>
						                </form>
						            </div>
						          </li>`).join('') +
	    				`</ul>` +
	      			`</div>` +
	      			`<div class="add-recipe">` +
		      			`<form>` +
		      				`<button id="addRecipe" type="submit">Add New Recipe</button>` +
		      			`</form>` +
	      			`</div>` +
	      		`</div>`;  		
  	};

  	const newRecipePage = function() {
  		return `<div class="addNewRecipe">` +
	  				`<div class="newRecipe">` +
	                    `<h3>Add new recipe</h3>` +
	           	 	`</div>` +
        			`<div class="getInfo">` +
        				`<form>` +
					        `Title: <input class="name" type="text" name="title" required>` +
					        `Ingredients: <input class="ingredients" type="text" name="ingredients" required>` +
					        `Instructions: <input class="instructions" type="text" name="instructions" required>` +
					        `<button id="confirm" type="submit">Confirm</button>` +
        				`</form>` +
        			`</div>` +
        			`<div class="home">` +
        				`<form>` +
        					`<button id="returnHomeFromNewRecipePage" type="submit">Home</button>` +
        				`</form>` +
        			`</div>` +
        		`</div>`;    		
  	};

  	const recipeDetailPage = function( result ) {
	    return `<div class="recipeInfo">` +
	    			`<div class="recipeDetail">` +
	    				`<p class="title">${result.title}</p>` +
	    				`<p class="addIngredients">Ingredients: ${result.ingredients}</p>` +
	    				`<p class="addInstructions">Instructions: ${result.instructions}</p>` +
	    			`</div>` +
	    			`<div class="homeButton">` +
		    			`<form>` +
		    				`<button id="returnHomeFromDetailPage" type="submit">Home</button>` + 
		    			`</form>` +
	    			`</div>` +
	    		`</div>`;		
  	};

  	document.addEventListener('click', (event) => {
		checkRecipeClick(event);
		checkAddRecipeClick(event);
		checkHomeFromDetailPageClick(event);
		checkHomeFromNewRecipePageClick(event);
		checkConfirmClick(event);
	});

	const checkRecipeClick = function(event) {
		if(event.target && event.target.id == 'targetRecipe'){
			event.preventDefault();
		  	const title = event.target.innerHTML;
		    loadRecipeDetailPage(title);
		}
	}

	const checkAddRecipeClick = function(event) {
		if(event.target && event.target.id == 'addRecipe'){
			event.preventDefault();
	  		document.getElementById('recipe').innerHTML = newRecipePage();
	  		status.innerHTML = '';
		}
	}

	const checkHomeFromDetailPageClick = function(event) {
		if(event.target && event.target.id == 'returnHomeFromDetailPage'){
			event.preventDefault();
			loadHomePage();
		}
	}
	
  	const checkHomeFromNewRecipePageClick = function(event) {
		if(event.target && event.target.id == 'returnHomeFromNewRecipePage'){
			event.preventDefault();
			loadHomePage();
		}
	}

  	const checkConfirmClick = function(event) {
		if(event.target && event.target.id == 'confirm'){
  			if(document.querySelector(".name").value === "")
  			{
  				return false;
  			}
  			if(document.querySelector(".ingredients").value === "")
  			{
  				return false;
  			}
  			if(document.querySelector(".instructions").value === "")
  			{
  				return false;
  			}
  			event.preventDefault();
  			const title = document.querySelector(".name").value;
  			const ingredients = document.querySelector(".ingredients").value;
  			const instructions = document.querySelector(".instructions").value;
  			updateRecipeDetail(title, ingredients, instructions);
		    updateRecipe(title);
		    loadRecipeDetailPage(title);
		}
	}

  })();