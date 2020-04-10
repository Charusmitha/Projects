import React from 'react';

const WatchListMovie = ( { list, onRemoveClick } ) => {
  return (
		<ol className="movieWatchList">
			{list.map(movie => 
			<li className="watchListMovie" key={movie.id}>
				<div className="poster">
			    	{(movie.poster !== null) ? ( 
			    	<img src={"https://image.tmdb.org/t/p/w500/"+movie.poster} alt="Poster not available" width="300" height="450"/> ) : (
			    	<img src={"https://in.bmscdn.com/iedb/movies/images/website/poster/large/the-mighty-mimalayan-man-et00009785-24-03-2017-19-30-07.jpg"} alt="Poster not available" width="300" height="450"/> )
					}
			    </div>
			    <div className="details">
			        <p className="wListTitle">{movie.title}</p>
			        <p className="wListRelDate">{movie.relDate}</p>
			        <p className="wListOverview">{movie.overview}</p>
			        <p className="wListVoteCount">Vote Count: {movie.voteCount}</p>
			        <p className="wListVoteAvg">Vote Avg: {movie.voteAvg}</p>
			        <button className="remove" onClick={() => onRemoveClick(movie.id)}>Remove</button>
			    </div>
			</li>
			)}
		</ol>
  );
};

export default WatchListMovie;