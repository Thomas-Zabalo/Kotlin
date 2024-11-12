package com.thomas.applicationandroid

import TmdbActor
import TmdbMovie
import TmdbTv
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbTv>>(listOf())
    val actors = MutableStateFlow<List<TmdbActor>>(listOf())
    val movieDetails = MutableStateFlow<TmdbMovieDetail?>(null)
    val serieDetails = MutableStateFlow<TmdbSerieDetail?>(null)

    fun getFilmsInitiaux(apikey: String) {
        viewModelScope.launch {
            val result = api.lastmovies(apikey)
            movies.value = result.results
        }
    }

    fun getFilmsBySearch(apikey: String, query: String) {
        viewModelScope.launch {
            val result = api.searchmovies(apikey, query)
            movies.value = result.results
        }
    }

    fun getFilmsById(id: String, apikey: String) {
        viewModelScope.launch {
            val result = api.movieDetails(id, apikey)
            movieDetails.value = result
        }
    }

    fun getTvInitiaux(apikey: String) {
        viewModelScope.launch {
            val result = api.lastseries(apikey)
            series.value = result.results
        }
    }

    fun getTvById(id: Int, apikey: String) {
        viewModelScope.launch {
            val result = api.serieDetails(id, apikey)
            serieDetails.value = result
        }
    }

    fun getActeurInitiaux(apikey: String) {
        viewModelScope.launch {
            val result = api.lastactors(apikey)
            actors.value = result.results
        }
    }
}


