const wordGame = {
  wordGamePage: function(words, secretWord, isInvalid, isFound, turns) {
    return `
      <!DOCTYPE html>
      <html>
      <head>
        <link rel="stylesheet" href="words_game.css"/>
        <title>Word-game</title>
      </head>
      <body>
        <div id="word-game">
            <div class="words-list">
              <h3>WORD LIST</h3>
              ${wordGame.getValidWords(words)}
            </div>
            <div class=game-details>
              <div class="outgoing">
                ${wordGame.getOutgoing(words, secretWord, isInvalid, isFound, turns)}
              </div>
              <div class="turns">
                  ${wordGame.getTurns(words)}
              </div>
            </div>
            <div class="prev-words-matches">
              <h3>GUESSED WORDS - LETTER MATCHES</h3>
              ${wordGame.getPrevWordsAndMatches(words)}
            </div>   
        </div>
      </body>
      </html>
  `;
  },

  getValidWords: function(words) {
    return `<ul class="valid-words-list">` +
      Object.values(words.validWords).map( word => `
      <li>
        <div class="valid-words">
          <span class="valid-word">${word}</span>
        </div>
      </li>
    `).join('') +
    `</ul>`;
  },

  getOutgoing: function(words,secretWord,isInvalid, isFound, turns) {
    if(isInvalid === true)
    {
    return `
       <div class="outgoing">
       <form action="/word" method="POST">
          <input class="to-send" name="text" value="" placeholder="Enter the secret word from the list"/>
          <input type="hidden" id="random" value=${secretWord}  name="secretWord" />
          <button type="submit">Submit</button>
          <script>
            alert("Invalid Word!!");
          </script>
      </form>
      </div>
    `;
    }
    else if(isFound === true)
    {
      return `
         <div class="outgoing">
         <form action="/word" method="POST">
            <input class="to-send" name="text" value="" placeholder="Enter the secret word from the list"/>
            <input type="hidden" id="random" value=${secretWord}  name="secretWord" />
            <button type="submit">Submit</button>
            <script>
            if(window.confirm("You win!! Turns taken: ${turns+1}"))
            {
              window.location='/';
            }
            </script>
        </form>
        </div>
      `;
    }
    else
    {
      return `
         <div class="outgoing">
         <form action="/word" method="POST">
            <input class="to-send" name="text" value="" placeholder="Enter the secret word from the list"/>
            <input type="hidden" id="random" value=${secretWord}  name="secretWord" />
            <button type="submit">Submit</button>
        </form>
        </div>
      `;
    }
  },

  getPrevWordsAndMatches: function(words) {
    if (!Array.isArray(words.prevWords) || !words.prevWords.length)
    {
    return `
      <ol class="no-words"> 
        <li>No words to display</li>
      </ol>
      `;
    }
    else
    {
    return `<ol class="words-matches">` +
      words.prevWords.map( wordMatches => `
        <li>
            <div class="word-match">
              <span class="prev-guess">${wordMatches.word} --> ${wordMatches.matches}</span>
            </div>
        </li>
      `).join('') +
      `</ol>`;
    }
  },
  
  getTurns: function(words) {
    return `
      <p class=turn>Turns taken to guess</p>
      <p class=number>${words.prevWords.length}</p>
    `;
  },
};

module.exports = wordGame;
