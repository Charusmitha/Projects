import React from 'react';

class MovieDetails extends React.Component {
	
	render(){	
		return (	
			<div className="movieDetails">
				<div className="mDetailsError">
					<p className="mDetailsErrorStatus">{this.props.status}</p>
				</div>
				<div className="mListLoading">
		    		<p className="mListLoadingMsg">{this.props.loading}</p>
		    	</div>
				<div className="moreInfoToHome">
					<button className="moreInfoToHomeButton" onClick={this.props.onDetailsToHomeButtonClick} >Home</button>
				</div>
				<div className="moreInfo">
					<div className="moreInfoPoster">
		          		{(this.props.movie.poster_path !== null) ? ( 
				    	<img src={"https://image.tmdb.org/t/p/w500/"+this.props.movie.poster_path} alt="Poster not available" width="300" height="500"/> ) : (
				    	<img src={"https://in.bmscdn.com/iedb/movies/images/website/poster/large/the-mighty-mimalayan-man-et00009785-24-03-2017-19-30-07.jpg"} alt="Poster not available" width="300" height="500"/> )
						}
		          	</div>
		          	<div className="moreDetails">
			          	<div className="otherDetails">
			              <p className="mDetailsTitle">{this.props.movie.title}</p>
			              <p className="mDetailsRelDate">{this.props.movie.release_date}</p>
			              <p className="mDetailsOverview">{this.props.movie.overview}</p>
			              <p className="mDetailsVoteCount">Vote Count: {this.props.movie.vote_count}</p>
			              <p className="mDetailsVoteAvg">Vote Avg: {this.props.movie.vote_average}</p>
			            </div>
			            <div className="extraDetails">
			              {(this.props.details.length !== 0) && 
			              <a className="trailer" href={"https://www.youtube.com/watch?v="+this.props.details[0].key} target="_blank" rel="noopener noreferrer">Trailer</a> 
			              }
			              <button className={this.props.class} onClick={() => this.props.onAddWatchListClick(this.props.movie.id, this.props.movie.title, this.props.movie.release_date, this.props.movie.poster_path, this.props.movie.vote_count, this.props.movie.vote_average, this.props.movie.overview)} disabled={ this.props.addDisabled }>Add to Watchlist</button>
			              <button className="removeWatchList" onClick={() => this.props.onRemoveWatchListClick(this.props.movie.id)} disabled={ this.props.removeDisabled }>Remove From Watchlist</button>
			            </div>
		        	</div>
		    	</div>
			</div>
		);
	}
}

export default MovieDetails;