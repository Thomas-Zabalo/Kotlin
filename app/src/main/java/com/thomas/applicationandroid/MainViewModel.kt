package com.thomas.applicationandroid

import TmdbActor
import TmdbMovie
import TmdbTv
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.playlistjson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel() : ViewModel() {
    val movies = MutableStateFlow<List<TmdbMovie>>(listOf())
    val series = MutableStateFlow<List<TmdbTv>>(listOf())
    val actors = MutableStateFlow<List<TmdbActor>>(listOf())
    val movieDetails = MutableStateFlow<TmdbMovieDetail?>(null)
    val serieDetails = MutableStateFlow<TmdbSerieDetail?>(null)

    // Etat observable
    val playlist = MutableStateFlow<List<Playlist>>(listOf())


    //Requete sur les films
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

    //Requete sur les series
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

    fun getTvBySearch(apikey: String, query: String) {
        viewModelScope.launch {
            val result = api.searchseries(apikey, query)
            series.value = result.results
        }
    }

    //Requete sur les acteurs
    fun getActeurInitiaux(apikey: String) {
        viewModelScope.launch {
            val result = api.lastactors(apikey)
            actors.value = result.results
        }
    }

    fun getActeurBySearch(apikey: String, query: String) {
        viewModelScope.launch {
            val result = api.searchacteurs(apikey, query)
            actors.value = result.results
        }
    }


    //Fetch de la playlist
    fun fetchPlaylist(): Playlist {
        val moshi = Moshi.Builder().build()
        return moshi.adapter(Playlist::class.java).fromJson(playlistjson)!!
    }

    fun getfetchPlaylist(){
        fetchPlaylist()
    }
}


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val api: Api = retrofit.create(Api::class.java)


