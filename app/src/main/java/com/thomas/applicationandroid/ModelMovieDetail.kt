package com.thomas.applicationandroid

class TmdbMovieDetail(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val belongs_to_collection: Any? = Any(),
    val budget: Int? = 0,
    val credits: Credits? = Credits(),
    val genres: List<Genre?>? = listOf(),
    val homepage: String? = "",
    val id: Int? = 0,
    val imdb_id: String? = "",
    val origin_country: List<String?>? = listOf(),
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val production_companies: List<Any?>? = listOf(),
    val production_countries: List<Any?>? = listOf(),
    val release_date: String? = "",
    val revenue: Int? = 0,
    val runtime: Int? = 0,
    val spoken_languages: List<SpokenLanguage?>? = listOf(),
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)

class Credits(
    val cast: List<Cast?>? = listOf(),
    val crew: List<Crew?>? = listOf()
)

class Genre(
    val id: Int? = 0,
    val name: String? = ""
)

class SpokenLanguage(
    val english_name: String? = "",
    val iso_639_1: String? = "",
    val name: String? = ""
)

class Cast(
    val adult: Boolean? = false,
    val cast_id: Int? = 0,
    val character: String? = "",
    val credit_id: String? = "",
    val gender: Int? = 0,
    val id: Int? = 0,
    val known_for_department: String? = "",
    val name: String? = "",
    val order: Int? = 0,
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)

class Crew(
    val adult: Boolean? = false,
    val credit_id: String? = "",
    val department: String? = "",
    val gender: Int? = 0,
    val id: Int? = 0,
    val job: String? = "",
    val known_for_department: String? = "",
    val name: String? = "",
    val original_name: String? = "",
    val popularity: Double? = 0.0,
    val profile_path: String? = ""
)