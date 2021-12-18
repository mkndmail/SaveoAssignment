package com.example.movieappbms.uicontroller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.movieappbms.R
import com.example.movieappbms.adapter.EndlessRecyclerViewScrollListener
import com.example.movieappbms.adapter.MovieListAdapter
import com.example.movieappbms.adapter.SlidingImagesAdapter
import com.example.movieappbms.databinding.ActivityMainBinding
import com.example.movieappbms.models.Results
import com.example.movieappbms.network.Status
import com.example.movieappbms.viewmodels.MainViewModel
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var pageNum = 1
    private var apiSearchResults = arrayListOf<Results>()
    lateinit var scrollListener: EndlessRecyclerViewScrollListener

    //    Initialise view model
    private val mainViewModel: MainViewModel by viewModels()


    //    Initialise Movie Adapter by delegation
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter(
            MovieListAdapter.BookMovieListener {
                Intent(this, MovieDetailsActivity::class.java).apply {
                    putExtra(MOVIE_DATA, it)
                }.also {
                    startActivity(it)
                }
            }
        )
    }

    private fun showNoResultsFound(resultString: String) {
        mainViewModel.setNoResultsFound(
            getString(R.string.no_results_found),
            Status.NO_RESULTS_FOUND
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        layoutInflater
        mainBinding.lifecycleOwner = this
        mainBinding.mainViewModel = mainViewModel
        mainBinding.rvMoviesList.apply {
            adapter = movieListAdapter
        }

        /*
        * Observing  the changes to data and populate UI accordingly
        * */
        mainViewModel.nowPlayingResponse.observe(this, {
            it?.let {
                apiSearchResults.addAll(it.results)
                movieListAdapter.updateData(it.results)

            }
        })

        mainViewModel.status.observe(this, {
            mainBinding.status = it
        })

        mainViewModel.noResultsFoundString.observe(this, { errorString ->
            errorString?.let {
                mainBinding.noResultsFoundString = it
            }
        })
        mainViewModel.callPopularMovies()
        mainViewModel.popularMoviesResponse.observe(this, { popularMoviesResponse ->
            if (popularMoviesResponse != null) {
                setUpViewPager(popularMoviesResponse.results.take(5) as ArrayList<Results>)
            }
        })

        scrollListener = object :
            EndlessRecyclerViewScrollListener(mainBinding.rvMoviesList.layoutManager as GridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                callNextPage()
            }
        }

        mainBinding.rvMoviesList.addOnScrollListener(scrollListener)
    }

    companion object {
        const val MOVIE_DATA = "movie_data"
    }

    fun callNextPage() {
        pageNum += 1
        mainViewModel.callNowPlaying(pageNum)
    }

    private fun setUpViewPager(images: ArrayList<Results>) {
        var currentPage = 0
        if (images.isNotEmpty()) {
            mainBinding.viewPager.clipToPadding = false
            mainBinding.viewPager.clipChildren = false
            mainBinding.viewPager.isSaveEnabled = false
            mainBinding.viewPager.offscreenPageLimit = 2
            mainBinding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val slidingAdapter =
                SlidingImagesAdapter(
                    this,
                    images
                )
            mainBinding.viewPager.adapter = slidingAdapter
            val totalCount = images.size
            val slidingImageDots: Array<ImageView?> = arrayOfNulls(totalCount)


//        viewPagerId.setPadding(70, 0, 70, 0)

// increase this offset to increase distance between 2 items
            val marginTransformer = MarginPageTransformer(10)
            mainBinding.viewPager.setPageTransformer(marginTransformer)
            val handler = Handler()
            val update = Runnable {
                if (currentPage == if (images.size == 0) 0 else Int.MAX_VALUE) {
                    currentPage = 0
                }
                mainBinding.viewPager.setCurrentItem(currentPage++, true)
            }

            Timer().schedule(object : TimerTask() {
                override fun run() {
                    handler.post(update)
                }
            }, 0, 4000)
        }
    }
}