const login = {
	loginPage: function() {
    return `
	 <!DOCTYPE html>
      <html>
        <head> 
          <title>Login</title>
        </head>
          <link rel="stylesheet" href="/chat.css"/>
        <body>
            <div class="login">
          	 <form action="/login" method="POST">
          		<input class="get-username" name="username" type="text" value="" placeholder="Enter username"/>
              <button class="login-button" type="submit">Login</button>
          	 </form>
            </div> 
        </body>
      </html>
    `;
	}
  };

  module.exports = login;
