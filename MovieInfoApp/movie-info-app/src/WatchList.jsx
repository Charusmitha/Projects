import React from 'react';
import WatchListMovie from './WatchListMovie';

const WatchList = ( { list, onRemoveButtonClick, onWatchListToHomeButtonClick, isWatchListEmptyMessage, loading, status } ) => {
  return (
  	<div className="watchList">
  		<div className="wListError">
			<p className="wListErrorStatus">{status}</p>
		</div>
  		<div className="wListLoading">
        	<p className="wListLoadingMsg">{loading}</p>
        </div>
	    <div className="homeButton">
	    	<button className="watchListToHome" onClick={onWatchListToHomeButtonClick}>Home</button>
	    </div>
	  	<div className="isListEmpty">
	  		<p className="watchListEmpty">{isWatchListEmptyMessage}</p>
	  	</div>
	  	<div className="list">
			<WatchListMovie list={list} onRemoveClick={(movieId) => onRemoveButtonClick(movieId)} />
		</div>
	</div>
  );
};

export default WatchList;