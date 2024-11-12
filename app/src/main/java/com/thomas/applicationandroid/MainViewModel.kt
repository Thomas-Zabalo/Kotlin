package com.thomas.applicationandroid

import TmdbMovie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())

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
}


