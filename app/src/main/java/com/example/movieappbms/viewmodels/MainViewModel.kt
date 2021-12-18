package com.example.movieappbms.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappbms.models.NowPlayingResponse
import com.example.movieappbms.network.NetworkRepository
import com.example.movieappbms.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val networkRepository: NetworkRepository by lazy {
        NetworkRepository()
    }

    init {
        callNowPlaying(1)
    }



    private val _nowPlayingResponse = MutableLiveData<NowPlayingResponse?>()
    val nowPlayingResponse: LiveData<NowPlayingResponse?>
        get() = _nowPlayingResponse
    private val _popularMoviesResponse = MutableLiveData<NowPlayingResponse?>()
    val popularMoviesResponse: LiveData<NowPlayingResponse?>
        get() = _popularMoviesResponse
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status


    private val _noResultsFoundString = MutableLiveData<Boolean>()
    val noResultsFoundString: LiveData<Boolean>
        get() = _noResultsFoundString

     fun callNowPlaying(pageNumber:Int) {
//         _status.postValue(Status.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = networkRepository.callGetNowPlaying(pageNumber)
                _status.postValue(Status.SUCCESS)
                _nowPlayingResponse.postValue(response)
            } catch (e: Exception) {
                Log.d("api_exception",e.stackTraceToString())
                _status.postValue(Status.ERROR)
            }
        }
    }

    fun callPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = networkRepository.getPopularMovies()
                _popularMoviesResponse.postValue(response)
            } catch (e: Exception) {
                Log.d("api_exception",e.stackTraceToString())
            }
        }
    }

    fun setNoResultsFound(string: String, status: Status) {
        _status.value = Status.NO_RESULTS_FOUND
        _noResultsFoundString.value = true
    }

    fun setResultsBack() {
        _status.value = Status.SUCCESS
        _noResultsFoundString.value = false
    }
}