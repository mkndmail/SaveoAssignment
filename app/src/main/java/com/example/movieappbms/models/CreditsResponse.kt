package com.example.movieappbms.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */
@JsonClass(generateAdapter = true)

/*
* This is a data class that parses the movie credits response to it's equivalent kotlin data class
* */
data class CreditsResponse(val id: Int, val cast: List<Cast>)

/*{
      "cast_id": 2,
      "character": "Jack Reese",
      "credit_id": "5e9c5c10371097001b486dd8",
      "gender": 2,
      "id": 89888,
      "name": "Adam Copeland",
      "order": 1,
      "profile_path": "/xawkWKkgbOx5atAMeFDh9rjHGWt.jpg"
    }*/
@JsonClass(generateAdapter = true)
data class Cast(
    @Json(name = "cast_id") val castID: Int,
    val character: String,
    @Json(name = "credit_id") val creditID: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    @Json(name = "profile_path") val profilePath: String?
)


