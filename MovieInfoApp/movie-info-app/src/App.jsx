import React, { Component } from 'react';
import './App.css';
import Login from './Login';
import MovieList from './MovieList';
import MovieDetails from './MovieDetails';
import WatchList from './WatchList';
import { getSearchResults, getMovieDetails, addToWatchList, removeFromWatchList, getWatchListForUser, getWatchListToCheckMoviePresence } from './services';

class App extends Component {

  constructor(){
    super();
    this.state = {
      movies: [],
      details: [],
      watchList: [],
      selectedMovie: {},
      showDetails: false,
      showWatchList: false,
      loggedIn: false,
      loggedInUser: '',
      isAddedToWatchList: false,
      isEmptyMessage: '',
      isWatchListEmptyMessage: '',
      loading: '',
      status: '',
    };
    this.handleLogin = this.handleLogin.bind(this);
    this.handleLoginButton = this.handleLoginButton.bind(this);
    this.handleKeyPress = this.handleKeyPress.bind(this);
    this.handleFilter = this.handleFilter.bind(this);
    this.handleMoreInfoClick = this.handleMoreInfoClick.bind(this);
    this.handleDetailsToHomeClick = this.handleDetailsToHomeClick.bind(this);
    this.handleAddWatchListClick = this.handleAddWatchListClick.bind(this);
    this.handleWatchListButtonClick = this.handleWatchListButtonClick.bind(this);
    this.handleRemoveWatchListClick = this.handleRemoveWatchListClick.bind(this);
    this.handleLogoutClick = this.handleLogoutClick.bind(this);
  }

  displayMovies = ( movie ) => {
    getSearchResults( movie )
      .then(searchResults => {
        const results = searchResults.results;
        if(results.length === 0)
        {
          this.setState({ movies: [], isEmptyMessage: 'No results found', status: '' })
        }
        else
        {
          results.sort(function(a,b){
            return (b.popularity) - (a.popularity)
          });
          this.setState({ movies: results, isEmptyMessage: '', status: '' })
        }
      })
      .catch( err => {
        if(err.error === 'network')
        {
          this.setState( { status: 'The network failed, please try again' });
        }
        if(err.error === 'notFound')
        {
          this.setState( { movies: [], isEmptyMessage: 'No results found', status: ''});
        }
      });
  }

  handleLogin = (user) => {
    this.setState( { loggedInUser: user, status: '' });
  }

  handleLoginButton = (event) => {
    this.setState( { loggedIn: true });
    event.preventDefault();
  }

  handleKeyPress = (event) => {
    if(event.keyCode === 13 && !event.target.value.replace(/\s/g, '').length)
    {
      this.setState( { isEmptyMessage: 'Please enter a movie title' });
    }
    else if(event.keyCode === 13 && event.target.value !== '')
    {
      this.displayMovies(event.target.value);
      event.target.value = '';
    }
  };

  handleFilter = (event) => {
    if(event.target.value === 'mostRecent')
    {
      let movieResults = this.state.movies;
      movieResults.sort(function(a,b){
          return new Date(b.release_date) - new Date(a.release_date)
      });
      this.setState({ movies: movieResults })

    }
    else if(event.target.value === 'leastRecent')
    {
      let movieResults = this.state.movies;
      movieResults.sort(function(a,b){
          return new Date(a.release_date) - new Date(b.release_date)
      });
      this.setState({ movies: movieResults })
    }
    else if(event.target.value === 'popularity')
    {
      let movieResults = this.state.movies;
      movieResults.sort(function(a,b){
          return (b.popularity) - (a.popularity)
      });
      this.setState({ movies: movieResults })
    }
  }

  handleMoreInfoClick = ( movieId, user ) => {
    this.setState({ loading: 'Loading...' }, () => {
      getMovieDetails( movieId )
        .then( movieDetails => {
          const details = movieDetails.results;
          const id = movieDetails.id;
          const movie = this.state.movies.find(movie => movie.id === id);
          this.setState({ details: details, selectedMovie: movie, showDetails: true, status: '', loading: ''}); 
          this.checkMovieInWatchList(movie.id);        
        })
        .catch( err => {
          if(err.error === 'network')
          {
            this.setState( { status: 'The network failed, please try again', loading: '' });
          }
          if(err.error === 'unknown')
          {
            this.setState( { status: 'An error occured, please try again', loading: '' });
          }
        });  
    }) 
  };

  checkMovieInWatchList = ( movieId ) => {
    const user = this.state.loggedInUser;
    getWatchListToCheckMoviePresence( movieId, user)
      .then( tempWatchList => {
        if(tempWatchList.length === 0)
        {
          this.setState({ isAddedToWatchList: false});
        }
        else 
        {
          this.setState({ isAddedToWatchList: true});
        }
      })
      .catch( err => {
        if(err.error === 'network')
        {
          this.setState( { status: 'The network failed, please try again', loading: '' });
        }
        if(err.error === 'unknown')
        {
          this.setState( { status: 'An error occured, please try again', loading: '' });
        }
      });
  }

  handleDetailsToHomeClick = () => {
    this.setState({
      showDetails: false
    })
  };

  handleWatchListToHomeButtonClick = () => {
    this.setState({
      showDetails: false,
      showWatchList: false,
      isEmptyMessage: '',
    })
  }

  handleAddWatchListClick = (id, title, relDate, poster, voteCount, voteAvg, overview) => {
    const user = this.state.loggedInUser;
    addToWatchList( id, user, title, relDate, poster, voteCount, voteAvg, overview )
      .then( () => {
        this.setState( { status: ''});
      })
      .catch( err => {
          if(err.error === 'network')
          {
            this.setState( { isAddedToWatchList: false, status: 'The network failed, please try again' });
          }
          if(err.error === 'unknown')
          {
            this.setState( { isAddedToWatchList: false, status: 'An error occured, please try again' });
          }
      });
    this.setState({ isAddedToWatchList: true, status: '' });
  }

  handleRemoveWatchListClick = (id) => {
    const user = this.state.loggedInUser;
    removeFromWatchList( id, user)
      .then( () => {
        this.setState( { status: ''});
      })
      .catch( err => {
          if(err.error === 'network')
          {
            this.setState( { isAddedToWatchList: true, status: 'The network failed, please try again' });
          }
          if(err.error === 'unknown')
          {
            this.setState( { isAddedToWatchList: true, status: 'An error occured, please try again' });
          }
      });
    this.setState({ isAddedToWatchList: false, status: '' });
  }

  handleRemoveButtonClick = (id) => {
    const user = this.state.loggedInUser;
    this.setState({ loading: 'Loading...' }, () => {
        removeFromWatchList( id, user)
          .then( () => {
            this.setState( { status: ''});
          })
          .catch( err => {
              if(err.error === 'network')
              {
                this.setState( { status: 'The network failed, please try again', loading: '' });
              }
              if(err.error === 'unknown')
              {
                this.setState( { status: 'An error occured, please try again', loading: '' });
              }
          });
       getWatchListForUser( user )
          .then( WatchList => {
            if(WatchList.length === 0)
            {
              this.setState({ watchList: WatchList, showWatchList: true, showDetails: false, isAddedToWatchList: false, isWatchListEmptyMessage: 'Watchlist is Empty', loading: '', status: '' });
            }
            else
            {
              this.setState({ watchList: WatchList, showWatchList: true, showDetails: false, isAddedToWatchList: false, isWatchListEmptyMessage: '', loading: '', status: '' });
            }
          })
          .catch( err => {
            if(err.error === 'network')
            {
              this.setState( { status: 'The network failed, please try again', loading: '' });
            }
            if(err.error === 'unknown')
            {
              this.setState( { status: 'An error occured, please try again', loading: '' });
            }
          });
    })
  }

  handleWatchListButtonClick = () => {
    const user = this.state.loggedInUser;
    this.setState({ loading: 'Loading...' }, () => {
      getWatchListForUser( user )
        .then( userWatchList => {
          if(userWatchList.length === 0)
          {
            this.setState({ watchList: userWatchList, showWatchList: true, showDetails: false, isWatchListEmptyMessage: 'Watchlist is Empty', loading: '', status: '' });
          }
          else
          {
            this.setState({ watchList: userWatchList, showWatchList: true, showDetails: false, isWatchListEmptyMessage: '', loading: '', status: '' });
          }
        })
        .catch( err => {
            if(err.error === 'network')
            {
              this.setState( { status: 'The network failed, please try again', loading: '' });
            }
            if(err.error === 'unknown')
            {
              this.setState( { status: 'An error occured, please try again', loading: '' });
            }
        });
    })
  }

  handleLogoutClick = () => {
    this.setState({ loggedIn: false, loggedInUser: '', movies: [] });
  }

  render() {
      const showDetails = this.state.showDetails;
      const loggedIn = this.state.loggedIn;
      const showWatchList = this.state.showWatchList;

      if(!loggedIn)
      {
        return(
            <div className="loginPage">
              <Login onLoginButton={ this.handleLoginButton } onLogin={ this.handleLogin } disabled={ !this.state.loggedInUser } loggedInUser={ this.state.loggedInUser } />
            </div>
          )
      }
      if(!showDetails && loggedIn && !showWatchList)
      {
      let filter = this.state.movies.length ? "filterVisible" : "filterNotVisible";
        return (
          <div className="movieSearchResults">
            < MovieList movies={this.state.movies} user={this.state.loggedInUser} onEnter={this.handleKeyPress} onMoreInfoButtonClick={(movieId, user) => this.handleMoreInfoClick(movieId, user)} onWatchListButtonClick={this.handleWatchListButtonClick} onLogoutClick={this.handleLogoutClick} onFilter={this.handleFilter} class={ filter } isEmptyMessage={this.state.isEmptyMessage} loading={this.state.loading} status={this.state.status} /> 
          </div>
        )

      }
      if(showDetails && loggedIn)
      { 
        let watchListButton = this.state.isAddedToWatchList ? "addedToWatchList" : "notAddedToWatchList";
        return (
          <div className="movieSearchDetails">
           < MovieDetails movie={this.state.selectedMovie} details={this.state.details} onDetailsToHomeButtonClick={this.handleDetailsToHomeClick} onAddWatchListClick={(id, title, relDate, poster, voteCount, voteAvg, overview) => this.handleAddWatchListClick(id, title, relDate, poster, voteCount, voteAvg, overview)} onRemoveWatchListClick={(id) => this.handleRemoveWatchListClick(id)} class={watchListButton} removeDisabled={ !this.state.isAddedToWatchList } addDisabled={ this.state.isAddedToWatchList } status={this.state.status} loading={this.state.loading}/> 
          </div>
        )
      }
      if(showWatchList && loggedIn)
      {
        return(
          <div className="userWatchList">
            < WatchList list={this.state.watchList} onRemoveButtonClick={(id) =>this.handleRemoveButtonClick(id)} onWatchListToHomeButtonClick={this.handleWatchListToHomeButtonClick} isWatchListEmptyMessage={this.state.isWatchListEmptyMessage} loading={this.state.loading} status={this.state.status} /> 
          </div>
        )
      }
    }
  
}

export default App;
