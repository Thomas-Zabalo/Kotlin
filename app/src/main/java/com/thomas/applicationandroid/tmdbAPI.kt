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
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchmovies(
        @Query("api_key") api_key: String,
        @Query("query") searchtext: String,
        @Query("language") language: String = "fr"
    ): TmdbMovieResult

    @GET("movie/{id}")
    suspend fun movieDetails(
        @Path("id") id: String,
        @Query("append_to_response") appendToResponse: String = "credits",
        @Query("language") language: String = "fr"
    ): TmdbMovieDetail

    @GET("trending/tv/week")
    suspend fun lastseries(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbTvResult


    @GET("trending/person/week")
    suspend fun lastactors(
        @Query("api_key") api_key: String,
        @Query("language") language: String = "fr"
    ): TmdbActorResult


}


val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val api: Api = retrofit.create(Api::class.java)
