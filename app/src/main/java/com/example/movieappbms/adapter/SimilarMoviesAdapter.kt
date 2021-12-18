package com.example.movieappbms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappbms.databinding.SimilarMoviesListBinding
import com.example.movieappbms.models.SimilarResults

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

class SimilarMoviesAdapter :
    ListAdapter<SimilarResults, RecyclerView.ViewHolder>(SimilarResultsDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder.from(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val similarResults = getItem(position)
        (holder as ViewHolder).bind(similarResults)
    }

    class ViewHolder private constructor(private val binding: SimilarMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(results: SimilarResults) {
            binding.similarResults = results
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SimilarMoviesListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class SimilarResultsDiffCallBack : DiffUtil.ItemCallback<SimilarResults>() {
        override fun areItemsTheSame(oldItem: SimilarResults, newItem: SimilarResults) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: SimilarResults, newItem: SimilarResults): Boolean {
            return oldItem == newItem
        }
    }
}