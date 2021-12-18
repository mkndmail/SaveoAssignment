package com.example.movieappbms.network

import com.example.movieappbms.models.*

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */


/*
* The repository class call's the APIs and returns the results to ViewModel
* */
class NetworkRepository {
    private val apiClient by lazy {
        Api.retrofitService
    }

    suspend fun callGetNowPlaying(pageNumber: Int): NowPlayingResponse? {
        return apiClient.getNowPlaying(pageNumber = pageNumber)
    }

    suspend fun getMovieCredits(movieID: Int): CreditsResponse {
        return apiClient.getMovieCredits(movieID)
    }

    suspend fun getMovieReview(movieID: Int): MovieReviewResponse {
        return apiClient.getMovieReview(movieID)
    }

    suspend fun getSimilarMovies(movieID: Int): SimilarMovieResponse? {
        return apiClient.getSimilarMovies(movieID)
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return apiClient.getMovieDetails(movieId)
    }

    suspend fun getPopularMovies(): NowPlayingResponse? {
        return apiClient.getPopularMovies()
    }
}