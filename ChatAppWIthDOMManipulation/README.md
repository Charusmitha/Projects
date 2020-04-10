

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


* The application will have front end javascript that will manipulate the DOM after page is loaded

Following features will be made available by the front end javascript:
- The "login" button will be disabled if there is no username provided
- The "send" button will be disabled if there is no message typed
- Clicking on a username in the user list will do the below (or undo them if this username is already selected)
  * The username in the user list that is selected will be shown in a different color
  * Any previous (different) username selected will remain selected
  * This will change the list of visible messages 
  The message list will:
  * show all messages when no usernames are selected
  * show only messages from selected usernames when at least one username is selected
  * show an indicator when messages are not visible at the space where 1 or more messages would be
  * This indicators should be thin - big enough to be visible, small enough to be smaller than an actual message.
  * Multiple indicators next to each other are not allowed.
  * If there are 3 "hidden" messages between 2 visible messages, there will be only 1 indicator (message, indicator, message)
  * If there are 3 visible messages and 2 hidden messages that alternate, each hidden message is indicated (message, indicator, message, indicator)
- There will be a button at the end of the user list to "Unselect All"
- This button is only visible if a username is selected
- When clicked, all usernames are deselected and all messages become visible



