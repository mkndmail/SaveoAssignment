package com.example.movieappbms.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

@JsonClass(generateAdapter = true)

/*
* This is a data class that parses the NowPlayingMovie response to it's equivalent kotlin data class
* */
data class NowPlayingResponse(
    val results: List<Results>,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Results(
    val id: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "title") val title: String,
    @Json(name = "vote_average)") val voteAverage: Double?,
    val overview: String,
    @Json(name = "release_date") val releaseDate: String
) : Parcelable
