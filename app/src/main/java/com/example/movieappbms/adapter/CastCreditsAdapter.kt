package com.example.movieappbms.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappbms.databinding.LayoutCreditsBinding
import com.example.movieappbms.models.Cast

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */

class CastCreditsAdapter :ListAdapter<Cast,RecyclerView.ViewHolder>(CastDiffCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cast=getItem(position)
        (holder as ViewHolder).bind(cast)
    }

    class ViewHolder private constructor(private val binding:LayoutCreditsBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(cast: Cast){
            binding.cast=cast
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCreditsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    class CastDiffCallBack:DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.castID==newItem.castID
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem==newItem
        }

    }

}