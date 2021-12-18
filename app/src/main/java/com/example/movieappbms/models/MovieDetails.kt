package com.example.movieappbms.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

@JsonClass(generateAdapter = true)

/*
* This is a data class that parses the movie details response to it's equivalent kotlin data class
* */
data class MovieDetails(
    @Json(name = "backdrop_path") val backdropPath: String,
    val genres: List<Genres>,
    val id: Int,
    @Json(name = "original_title") val title: String,
    val overview: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompanies>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int,
    val revenue:Int
)
@JsonClass(generateAdapter = true)
data class Genres(val id: Int, val name: String)
@JsonClass(generateAdapter = true)
data class ProductionCompanies(
    val id: Int,
    val name: String,
    @Json(name = "origin_country") val originCountry: String
)