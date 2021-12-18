package com.example.movieappbms.network

import com.example.movieappbms.BuildConfig
import com.example.movieappbms.models.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

/*
* This is a Network client to communicate with the server
* */
private const val BASE_URL = BuildConfig.BASE_URL

private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    NO_RESULTS_FOUND
}

interface DataService {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") api_key: String = BuildConfig.API_KEY,@Query("page") pageNumber:Int): NowPlayingResponse?

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String = BuildConfig.API_KEY): NowPlayingResponse?

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieDetails

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieReviewResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): CreditsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): SimilarMovieResponse?
}

object Api {
    val retrofitService: DataService by lazy {
        retrofit.create(DataService::class.java)
    }
}