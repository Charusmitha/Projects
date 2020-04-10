import React from 'react';
import Movie from './Movie';

class MovieList extends React.Component {

	render(){
		return (
			<div className="movieResults">
				<div className="mListError">
					<p className="mListErrorStatus">{this.props.status}</p>
				</div>
				<div className="mListLoading">
	            	<p className="mListLoadingMsg">{this.props.loading}</p>
	            </div>
	            <div className="userDetails">
		            <div className="title">
		            	<p className="appTitle">MovieFlixDB</p>
		            </div>
					<div className="user">
						<p className="username">{this.props.user}</p>
					</div>
					<div className="displayWatchList">
		            	<button className="watchListButton" onClick={this.props.onWatchListButtonClick}>Watch List</button>
		            </div>
		            <div className="logout">
						<button className="logoutButton" onClick={this.props.onLogoutClick}>Logout</button>
					</div>
				</div>
				<div className="searchAndFilter">
					<div className="searchMovie">
		              	<input className="searchBox" name="text" onKeyDown={this.props.onEnter} placeholder="Search for your desired movie" />
		            </div>
					<div className={this.props.class}>
						Filter <select value={this.value} onChange={this.props.onFilter} >
									<option value="popularity">Popularity</option>
	            					<option value="mostRecent">Newest</option>
	            					<option value="leastRecent">Oldest</option>
	          					</select>
					</div>
				</div>
	            <div className="emptyList">
	            	<p className="emptyListMsg">{this.props.isEmptyMessage}</p>
	            </div>
	            <div className="movies">
					<Movie movies={this.props.movies} onMoreInfoClick={(movieId, user) => this.props.onMoreInfoButtonClick(movieId, user)} user={this.props.user} />
	            </div>
          	</div>
		)
	}
}

export default MovieList;