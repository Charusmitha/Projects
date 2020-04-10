import React from 'react';

const Movie = ( { movies, onMoreInfoClick, user } ) => {
  return (
		<ol className="movieList">
			{movies.map(movie => 
			<li className="movie" key={movie.id}>
				<div className="moviePoster">
					{(movie.poster_path !== null) ? ( 
			    	<img src={"https://image.tmdb.org/t/p/w500/"+movie.poster_path} alt="Poster not available" width="200" height="300"/> ) : (
			    	<img src={"https://in.bmscdn.com/iedb/movies/images/website/poster/large/the-mighty-mimalayan-man-et00009785-24-03-2017-19-30-07.jpg"} alt="Poster not available" width="200" height="300"/>
			    	)
					}
			    </div>
			    <div className="movieInfo">
			        <p className="movieTitle">{movie.title}</p>
			        <p className="movieRelDate">{movie.release_date}</p>
			        <p className="popularity">Popularity: {movie.popularity}</p>
			        <button className="moreInfoButton" onClick={() => onMoreInfoClick(movie.id, user)}>More Info</button>
			    </div>
			</li>
			)}
		</ol>
  );
};

export default Movie;