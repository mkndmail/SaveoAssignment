package com.example.movieappbms.bindingutils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.movieappbms.BuildConfig
import com.example.movieappbms.databinding.GenreLayoutBinding
import com.example.movieappbms.models.Genres
import com.example.movieappbms.network.Status


/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */
@BindingAdapter("setImage")
//This extension functions loads images and set on concerned image view
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        this.load("${BuildConfig.IMAGE_URL}$it")
    }
}

@BindingAdapter("addDynamicViews")
//This extension function create dynamic views and adds to LinearLayout
fun LinearLayout.setDynamicViews(genres: List<Genres>?) {
    genres?.let {
        if (genres.size > 3) {
            genres.take(3).forEach {
                setGenres(it)
            }
        } else genres.forEach {
            setGenres(it)
        }
    }
}

private fun LinearLayout.setGenres(it: Genres) {
    val binding = GenreLayoutBinding.inflate(LayoutInflater.from(this.context))
    binding.txtGenre.text = it.name
    this.addView(binding.root)
}

@BindingAdapter("progressbarVisibility")
//To control the visibility of ProgressBar
fun ProgressBar.progressBarVisibility(status: Status?) {
    status?.let {
        when (status) {
            Status.LOADING -> {
                this.visibility = VISIBLE
            }
            else -> {
                this.visibility = GONE
            }
        }
    }

}

@BindingAdapter("spanText")
//This is an unused function
fun TextView.setSpanText(text: Double) {
    val initialString = "Rating ".apply {
        append(text.toString())
    }
    val spannableString = SpannableString(initialString)
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        7,
        spannableString.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}



