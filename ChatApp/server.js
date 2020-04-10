const express = require('express');
const app = express();

const PORT = 3000;

const login = require('./login');
const chat = require('./chat');
const chatWeb = require('./chat-web');

app.use(express.static('./public'));

app.get('/', (req, res) => {	
	const curr_user = req.query.curr_user;
	if(curr_user === undefined || !(curr_user in chat.users))
	{
		res.redirect('/login');
	}
	else
	{
 		res.send(chatWeb.chatPage(chat, curr_user));
	}
});

app.get('/login', (req, res) => {
	res.send(login.loginPage());
});

app.post('/login', express.urlencoded({ extended: false }), (req, res) => {
	const userKey = req.body.username;
	const query = "curr_user="+userKey;
	chat.users[userKey] = req.body.username;
	if (!chat.removedDefault) 
	{
		chat.messages.splice(0,2);
		const user1 = "Amit";
		const user2 = "Bao";
		delete chat.users[user1];
		delete chat.users[user2];
		chat.removedDefault = true;
	}
	res.redirect('/?' + query);
});

app.post('/chat', express.urlencoded({ extended: false }), (req, res) => {
	const sender = req.body.username; 
	const query = "curr_user="+sender;
	const { text } = req.body;
	chat.addMessage({ sender, text, timestamp: new Date() });
	res.redirect('/?' + query);
});

app.post('/logout', express.urlencoded({ extended: false }), (req, res) => {
	const userKey = req.query.curr_user;
	delete chat.users[userKey];
	res.redirect('/');
});

app.listen(PORT, () => console.log(`Listening on http://localhost:${PORT}`));
