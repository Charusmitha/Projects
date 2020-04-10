const express = require('express');
const app = express();
const path = require('path');
const fetch = require('node-fetch');
const PORT = 4000;

const watchList = [];
const apiKey = '9c55a2ea689d1fd21942e28d771b3f39';

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', function(req, res) {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/watchList/', express.json(), (req, res) => {
  let id = req.body.id;
  let user = req.body.user;
  let title = req.body.title;
  let relDate = req.body.relDate;
  let poster = req.body.poster;
  let voteCount = req.body.voteCount;
  let voteAvg = req.body.voteAvg;
  let overview = req.body.overview;
  watchList.push({ user, id, title, relDate, poster, voteCount, voteAvg, overview });
  res.sendStatus(200);
});

app.post('/notWatchList/', express.json(), (req, res) => {
  let id = req.body.id;
  let user = req.body.user;
  for(let i = watchList.length - 1; i >= 0; i--) {
    if(watchList[i].id === id && watchList[i].user === user) {
       watchList.splice(i, 1);
    }
  }
  res.sendStatus(200);
});

app.get('/watchList/:id&:user', (req,res, next) => {
  let id  = req.params.id;
  let user = req.params.user;
  const tempWatchList = [];
  for(let i = watchList.length - 1; i >= 0; i--) {
    if(watchList[i].id == id && watchList[i].user == user) {
      let id = watchList[i].id;
      let user = watchList[i].user;
      let title = watchList[i].title;
      let relDate = watchList[i].relDate;
      let poster = watchList[i].poster;
      let voteCount = watchList[i].voteCount;
      let voteAvg = watchList[i].voteAvg;
      let overview = watchList[i].overview;
      tempWatchList.push({ user, id, title, relDate, poster, voteCount, voteAvg, overview });
    }
  }
  res.json( tempWatchList );
});

app.get('/userWatchList/:user', (req,res, next) => {
  let user = req.params.user;
  const userWatchList = [];
  for(let i = watchList.length - 1; i >= 0; i--) {
    if(watchList[i].user == user) {
      let id = watchList[i].id;
      let user = watchList[i].user;
      let title = watchList[i].title;
      let relDate = watchList[i].relDate;
      let poster = watchList[i].poster;
      let voteCount = watchList[i].voteCount;
      let voteAvg = watchList[i].voteAvg;
      let overview = watchList[i].overview;
      userWatchList.push({ user, id, title, relDate, poster, voteCount, voteAvg, overview});
    }
  }
  res.json( userWatchList );
});

app.get('/movies/:movie', (req, res) => {
  const movie = req.params.movie;  
  fetch('https://api.themoviedb.org/3/search/movie?api_key='+apiKey+'&query='+movie)
      .catch( err => Promise.reject({ error: 'network', err }) )
      .then( response => {
        if(response.ok) {
          return response.json();
        }
        return Promise.reject({ error: 'notFound', err: response.statusText });
      })
      .then(searchResults => {
        return res.json( searchResults );
      });
});

app.get('/movieDetails/:movieId', (req, res) => {
  const movieId = req.params.movieId;  
  fetch('https://api.themoviedb.org/3/movie/'+movieId+'/videos?api_key='+apiKey+'')
      .catch( err => Promise.reject({ error: 'network', err }) )
      .then( response => {
        if(response.ok) {
          return response.json();
        }
        return Promise.reject({ error: 'unknown', err: response.statusText });
      })
      .then(movieDetails => {
        res.json( movieDetails );
      });
});

app.listen(PORT, () => console.log(`http://localhost:${PORT}`) );