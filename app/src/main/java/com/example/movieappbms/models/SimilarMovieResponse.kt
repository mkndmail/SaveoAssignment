package com.example.movieappbms.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

@JsonClass(generateAdapter = true)

/*
* This is a data class that parses the Similar Movie response to it's equivalent kotlin data class
* */
data class SimilarMovieResponse(
    val page: Int,
    val results: List<SimilarResults>?
)
@JsonClass(generateAdapter = true)
data class SimilarResults(
    @Json(name = "backdrop_path") val backdropPath: String,
    val id: Int,
    @Json(name = "original_language") val originalLanguage: String,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double
)
