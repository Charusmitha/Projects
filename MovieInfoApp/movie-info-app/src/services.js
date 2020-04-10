export const getSearchResults = ( movie ) => {
  return fetch(`/movies/${movie}`)
        .catch( err => Promise.reject({ error: 'network', err }) )
        .then( response => {
          if(response.ok) {
            return response.json();
          }
          return Promise.reject({ error: 'notFound', err: response.statusText });
      });
}

export const getMovieDetails = ( movieId ) => {
  return fetch(`/movieDetails/${movieId}`)
      .catch( err => Promise.reject({ error: 'network', err }) )
      .then( response => {
        if( response.ok ) 
        {
          return response.json();
        }
        return Promise.reject({ error: 'unknown', err: response.statusText });
      })
}

export const addToWatchList = ( id, user, title, relDate, poster, voteCount, voteAvg, overview ) => {
  return fetch('/watchList/', {
          method: 'POST',
          headers: new Headers({ 'content-type': 'application/json' }),
          body: JSON.stringify( { id, user, title, relDate, poster, voteCount, voteAvg, overview } )
          })
        .catch( err => Promise.reject({ error: 'network', err }) )
        .then( response => {
          if(response.ok) {
            return response.json();
          }
          return Promise.reject({ error: 'unknown', err: response.statusText });
        });
}

export const removeFromWatchList = ( id, user ) => {
  return fetch('/notWatchList/', {
          method: 'POST',
          headers: new Headers({ 'content-type': 'application/json' }),
          body: JSON.stringify( { id, user } )
          })
        .catch( err => Promise.reject({ error: 'network', err }) )
        .then( response => {
          if(response.ok) {
            return response.json();
          }
          return Promise.reject({ error: 'unknown', err: response.statusText });
      });
}

export const getWatchListToCheckMoviePresence = ( id, user ) => {
  return fetch(`/watchList/${id}&${user}`)
        .catch( err => Promise.reject({ error: 'network', err }) )
        .then( response => {
          if(response.ok) {
            return response.json();
          }
          return Promise.reject({ error: 'unknown', err: response.statusText });
          })
}

export const getWatchListForUser = ( user ) => {
  return fetch(`/userWatchList/${user}`)
        .catch( err => Promise.reject({ error: 'network', err }) )
        .then( response => {
          if(response.ok) {
            return response.json();
          }
          return Promise.reject({ error: 'unknown', err: response.statusText });
          })
}