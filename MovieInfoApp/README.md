## Apr 2019

# Movie Info Application

>This is a single page movie info web application created using create-react-app. The application uses a backend server that hosts the BUILT(using npm run build) React app as well as provides REST services for various functionalities. The application will be usable by multiple users simultaneously.

## Functionality
### Login page
  * The `login` button in the login page is disabled if there is no username started 
  * Once the user logs in, he/she will be taken to the home page
### Home page 
  * **Search box** - User can type his/her desired movie name or a string in the search box. Once ==enter== is pressed, the string entered by user will be sent to the server which will call the movieDB API to fetch the movie list based on the string sent. 
    * The results will be displayed on the home page. The search box will be cleared once ==enter== is pressed.
    * If the user provides only white spaces in the search box, a message will be displayed saying =='Please enter a movie title'==
    * If the user provides a string for which there are no results found from API, a message will be displayed saying =='No results found'==
  * **More Info button** - Each movie displayed on the home page will have a `more info` button that will take the user to ==More Info== page that will display more info about the movie
  * **Filter** - The filter option will be only visible when the movie list obtained from API is not empty. If it is empty, the filter will be hidden. User can filter movies based on popularity, release date (newest, oldest). The results will be sorted based on popularity by default
  * **WatchList button** - Clicking this button will take the user to watchlist page that will have a list of movies added to watchlist by the user
  * **Logout button** - Clicking this button will logout the user and take him/her to login page
### More Info Page
  * This page will have more details about a particular movie selected by the user. The details include title, overview, vote count, etc.
  * **Trailer button** - Clicking on Trailer button will open a new tab and play the movie trailer on ==YouTube==
  * **Add to Watchlist button** - Clicking this button will add the movie to the watchlist, turn the button to green and disable it to make sure the movie is only added once to the watchlist.
  * **Remove from Watchlist** - Clicking this button will remove the movie from the watchlist. The button is disabled if the movie is not in the watchlist.
  * **Home button** - This button will take the user to the home page containing the movie result list
### Watchlist Page
  * This page will have list of movies that are added to watchlist by user
  * **Remove button** - Clicking this button will remove the movie from the watchlist. In =='More Info'== page of that particular movie, `Add to watchlist` button will be enabled and color will be changed from green to grey, `Remove from watchlist` button will be disabled
  * **Home button** - This button will take the user to the home page containing the movie result list
  * If watchlist is empty, a message will be displayed saying =='Watchlist is Empty'==
 
### REST services:
  * **GET /movies/${movie}** - this service call will in turn fetch the details from the movieDB API for the string ${movie} entered by user
  * **GET /movieDetails/${movieId}** - this service call will in turn fetch the movie details for a particular movie(${movieId}) from movieDB API
  * **POST /watchList/** - this service call will post the movie details sent in the body to the watchList array present in server
  * **POST /notWatchList/** - this service call will delete the movie from the watchlist array present in the server for that particular user
  * **GET /watchList/${id}&${user}** - this service call will fetch the movie present in watchList array in the server for a particular user(${user}) and movie(${movieId})
  * **GET /userWatchList/${user}** - this service call will fetch the movies present in watchList array in the server for a particular user(${user})
  * For all the above REST services, a ==Loading..== message will be displayed while the service calls are being executed
  * For the network error, a message will be displayed saying =='The network failed, please try again'== and for all the unknown errors, =='An error occured, please try again'== will be displayed
  
