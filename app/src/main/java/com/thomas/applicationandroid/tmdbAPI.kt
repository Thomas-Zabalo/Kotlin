package com.thomas.applicationandroid

import TmdbActorResult
import TmdbMovieResult
import TmdbTvResult
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("trending/movie/week")
    suspend fun lastmovies(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchmovies(
        @Query("api_key") apikey: String,
        @Query("query") searchtext: String,
        @Query("language") language: String = "fr"
    ): TmdbMovieResult

    @GET("movie/{id}?append_to_response=credits")
    suspend fun movieDetails(
        @Path("id") id: String,
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): TmdbMovieDetail

    @GET("trending/tv/week")
    suspend fun lastseries(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): TmdbTvResult

    @GET("search/tv")
    suspend fun searchseries(
        @Query("api_key") apikey: String,
        @Query("query") searchtext: String,
        @Query("language") language: String = "fr"
    ): TmdbTvResult

    @GET("tv/{id}?append_to_response=credits")
    suspend fun serieDetails(
        @Path("id") id: Int,
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): TmdbSerieDetail


    @GET("trending/person/week")
    suspend fun lastactors(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "fr"
    ): TmdbActorResult

    @GET("search/person")
    suspend fun searchacteurs(
        @Query("api_key") apikey: String,
        @Query("query") searchtext: String,
        @Query("language") language: String = "fr"
    ): TmdbActorResult

}


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val api: Api = retrofit.create(Api::class.java)
