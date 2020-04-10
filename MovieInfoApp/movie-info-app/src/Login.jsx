import React from 'react';

const Login = ( { onLoginButton, onLogin, disabled, loggedInUser } ) => {
  return (
  	<div className="login">
		<form onSubmit={ onLoginButton }>
	    	<input className="loginBox" name="text" onChange={ (e) => onLogin(e.target.value) } value={ loggedInUser } />
	    	<button className="loginButton" disabled={ disabled } >Login</button>
		</form>
	</div>
  );
};

export default Login;