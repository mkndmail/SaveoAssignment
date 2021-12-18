package com.example.movieappbms.uicontroller

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappbms.adapter.CastCreditsAdapter
import com.example.movieappbms.adapter.SimilarMoviesAdapter
import com.example.movieappbms.databinding.ActivityMovieDetailsBinding
import com.example.movieappbms.models.Results
import com.example.movieappbms.uicontroller.MainActivity.Companion.MOVIE_DATA
import com.example.movieappbms.viewmodels.MovieDetailViewModel


class MovieDetailsActivity : AppCompatActivity() {

    private val activityMovieDetailsBinding: ActivityMovieDetailsBinding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val movieDetailViewModel by viewModels<MovieDetailViewModel>()
    private val similarMoviesAdapter: SimilarMoviesAdapter by lazy {
        SimilarMoviesAdapter()
    }

    private val castCreditsAdapter: CastCreditsAdapter by lazy {
        CastCreditsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMovieDetailsBinding.root)
        activityMovieDetailsBinding.lifecycleOwner = this
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityMovieDetailsBinding.rvCredits.apply {
            layoutManager = LinearLayoutManager(
                this@MovieDetailsActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = castCreditsAdapter
        }
        activityMovieDetailsBinding.rvSimilarMovies.apply {
            layoutManager = LinearLayoutManager(
                this@MovieDetailsActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = similarMoviesAdapter
        }
        val results = intent.extras?.getParcelable<Results>(MOVIE_DATA)
        results?.let {
            supportActionBar?.title = results.title
            activityMovieDetailsBinding.results = results
            movieDetailViewModel.getMovieDetails(results.id)
            movieDetailViewModel.getMovieCredits(results.id)
            movieDetailViewModel.getMovieReview(results.id)
            movieDetailViewModel.getSimilarMovies(results.id)
        }

        movieDetailViewModel.movieDetails.observe(this, {
            activityMovieDetailsBinding.movieDetails = it
        })

        movieDetailViewModel.similarMovies.observe(this, {
            it?.let {
                similarMoviesAdapter.submitList(it.results)
                if (it.results?.size == 0) activityMovieDetailsBinding.txtSimilarMovies.visibility =
                    GONE else activityMovieDetailsBinding.txtSimilarMovies.visibility =
                    VISIBLE
            }
        })

        movieDetailViewModel.movieCredits.observe(this, {
            castCreditsAdapter.submitList(it.cast)
        })

        movieDetailViewModel.status.observe(this, {
            activityMovieDetailsBinding.status = it
        })

        movieDetailViewModel.movieReview.observe(this, { reviewResponse ->
            if (reviewResponse != null) {
                reviewResponse.results?.let {
                    if (it.isNotEmpty()) {
                        activityMovieDetailsBinding.txtReview.text = it[0]?.content
                        activityMovieDetailsBinding.txtReHeading.visibility = VISIBLE
                    } else {
                        activityMovieDetailsBinding.txtReHeading.visibility = GONE
                        activityMovieDetailsBinding.txtReview.visibility = GONE
                    }
                }
            } else {
                activityMovieDetailsBinding.txtReHeading.visibility = GONE
                activityMovieDetailsBinding.txtReview.visibility = GONE
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            super.onBackPressed()
        return super.onOptionsItemSelected(item)

    }
}