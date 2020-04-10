

## Goal:

To design a web based chat application that will work for multiple users to have usernames, allow logout.

The App has the following features:

* `http://localhost:3000/` will check to see if the user is considered logged in
    * If they are, it will display the chat application
    * If they are not, it will redirect them to `/login` (GET) which will display a form asking for a username (name = 'username')
* The login form will 
   * POST (not GET) to `/login`
   * which will add the username to the user list, 
   * redirect them to `/`
* The base chat page
   * Will send their user name as a hidden field (name=username) to the server when posting a new message
   * Will have a "Refresh" button that loads the base chat page
* Will have a "Logout" button that sends (POST) the username (name=username) that will remove that user from the user list
* Messages from logged out users will still be visible



