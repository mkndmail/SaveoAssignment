package com.example.movieappbms.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappbms.models.CreditsResponse
import com.example.movieappbms.models.MovieDetails
import com.example.movieappbms.models.MovieReviewResponse
import com.example.movieappbms.models.SimilarMovieResponse
import com.example.movieappbms.network.NetworkRepository
import com.example.movieappbms.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails
    private val _movieCredits = MutableLiveData<CreditsResponse>()
    val movieCredits: LiveData<CreditsResponse>
        get() = _movieCredits
    private val _movieReview = MutableLiveData<MovieReviewResponse>()
    val movieReview: LiveData<MovieReviewResponse>
        get() = _movieReview
    private val _similarMovies = MutableLiveData<SimilarMovieResponse?>()
    val similarMovies: LiveData<SimilarMovieResponse?>
        get() = _similarMovies
    private val networkRepository: NetworkRepository by lazy {
        NetworkRepository()
    }


    fun getMovieDetails(movieId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = networkRepository.getMovieDetails(movieId)
                _status.postValue(Status.SUCCESS)
                _movieDetails.postValue(response)
            } catch (e: Exception) {
                _status.postValue(Status.ERROR)
                e.printStackTrace()
            }
        }
    }

    fun getMovieCredits(movieId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = networkRepository.getMovieCredits(movieId)
                _movieCredits.postValue(response)
            } catch (e: Exception) {
                _status.postValue(Status.ERROR)
                e.printStackTrace()
            }
        }
    }

    fun getMovieReview(movieId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = networkRepository.getMovieReview(movieId)
                _status.postValue(Status.SUCCESS)
                _movieReview.postValue(response)
            } catch (e: Exception) {
                _status.postValue(Status.ERROR)
                e.printStackTrace()
            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response :SimilarMovieResponse?= networkRepository.getSimilarMovies(movieId)
                _similarMovies.postValue(response)
            } catch (e: Exception) {
                _status.postValue(Status.ERROR)
                e.printStackTrace()
            }
        }
    }
}