const express = require('express');
const app = express();
const PORT = 3000;

const words = require('./words');
const wordGame = require('./wordGame');

app.use(express.static('./public'));

app.get('/', (req, res) => {
    const secretWord = words.validWords[Object.keys(words.validWords)[ Object.keys(words.validWords).length * Math.random() << 0]];
    console.log('Secret Word: ' + secretWord);
    res.send(wordGame.wordGamePage(words, secretWord , false, false));
});


app.post('/word', express.urlencoded({ extended: false }), (req, res) => {
  const sWord = req.body.secretWord;
	const word = req.body.text;
	if(word.toLowerCase() === sWord.toLowerCase())
	{
    isInvalid = false;
    isFound = true;
    const turns = words.prevWords.length;
    words.prevWords.splice(0,words.prevWords.length);
    res.send(wordGame.wordGamePage(words, sWord, isInvalid, isFound, turns));
	}
	else if ((!(word.toLowerCase() in words.validWords)) && (!(word.toUpperCase() in words.validWords)))
	{
    isFound = false;
    isInvalid = true;
    res.send(wordGame.wordGamePage(words, sWord, isInvalid, isFound));  
	}
	else
	{
    isFound = false;
    isInvalid = false;
		let matches = 0;
  	const mapping = {};
  	let newString = '';
  	for (let i = 0; i < sWord.toLowerCase().length; i++) 
  	{
    	if (!(sWord[i].toLowerCase() in mapping)) 
    	{
      	newString += sWord[i].toLowerCase();
      	mapping[sWord[i].toLowerCase()] = true;
    	}
  	}
  
		for (let i = 0; i < newString.length; i++) 
		{
      if(word.toLowerCase().indexOf(newString[i]) != -1)
      {
        matches = matches + 1;
      }
    }
   	words.addPrevWords({ word, matches });
    res.send(wordGame.wordGamePage(words, sWord, isInvalid, isFound));
  }   
});

app.listen(PORT, () => console.log(`Listening on http://localhost:${PORT}`));
