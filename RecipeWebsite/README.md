## June 2019

# Goal:

- To design a recipe storage and search website 
- The application will be a multiple-page web application that uses Progressive Enhancement to offer a single-page application version
- The application will use RESTful services

From the main page when a user loads the application:

- User can see a list of all recipe titles
- User can click a recipe title to see the recipe (title, ingredients, and instructions)
- User can add a new recipe (3 inputs: title, ingredients, and instructions)
- When not on the main page, user can return to the main page

Home Page

- Displays a list of all stored recipes
- Clicking on a recipe title will load a details page/screen
- Clicking on the "New Recipe" button will load the New Recipe page/screen

Recipe Details Page

- Displays the title, ingredients list, and instructions for the selected recipe
- User can click a "Return to Home" button to return to the Home Page

New Recipe Page

- Displays a form to enter the title, ingredients list, and instructions for a new recipe
- The ingredients list will be a single textarea field to enter the data
- The instructions list will be a single textarea field to enter the data
- The user is not allowed to enter a recipe without something present in all 3 fields
- The user can click a "Return to Home" button to return to the Home Page
- The user is put on the Recipe Details screen for the new recipe after successfully submitting a recipe.
