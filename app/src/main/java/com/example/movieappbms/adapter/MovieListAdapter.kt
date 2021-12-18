package com.example.movieappbms.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappbms.databinding.LayoutMovieDetailsBinding
import com.example.movieappbms.models.Results

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

class MovieListAdapter(
    private val clickListener: BookMovieListener,
//    private val results: ArrayList<Results>

) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>()/*ListAdapter<Results, RecyclerView.ViewHolder>(ResultDiffCallback())*/
    /*Filterable*/ {
    var results= arrayListOf<Results>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        (holder as ViewHolder).bind(clickListener, result)
    }

    fun updateData(newList: List<Results?>?) {
        newList?.forEach {
            results.add(it!!)
        }
        Log.d("api_list", "updateData: ${results.size}")
        notifyDataSetChanged()
    }

    class ViewHolder private constructor(private val binding: LayoutMovieDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: BookMovieListener, results: Results) {
            binding.results = results
            binding.executePendingBindings()
            binding.clickListener = clickListener
            binding.imgMoviePoster.setOnClickListener {
                clickListener.onClick(results)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutMovieDetailsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    class ResultDiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

    }

    class BookMovieListener(val clickListener: (result: Results) -> Unit) {
        fun onClick(result: Results) = clickListener(result)
    }

    /* override fun getFilter(): Filter {
         return object : Filter() {
             override fun performFiltering(searchTerm: CharSequence?): FilterResults {
                 val searchResults = arrayListOf<Results>()
                 val searchString = searchTerm.toString()
                 this@MovieListAdapter.currentList.forEach { results ->
                     val listTitle = results.title.split(" ")
                     listTitle.forEach {
                         if (it.startsWith(searchString, ignoreCase = true)) {
                             searchResults.add(results)
                         }
                     }
                 }
                 val filterResults = FilterResults()
                 filterResults.values = searchResults
                 return filterResults
             }

             override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                 val filteredResults:ArrayList<Results> = results?.values as ArrayList<Results>
                 if (filteredResults.isNotEmpty() && filteredResults.size > 0)
                     this@MovieListAdapter.submitList(filteredResults)
                 else
                     noResultsFound.noSearchResultFound("No results Found")
             }
         }
     }*/

    interface NoResultsFound {

        fun noSearchResultFound(resultString: String?)
    }

    override fun getItemCount(): Int {
        return results.size
    }
}

