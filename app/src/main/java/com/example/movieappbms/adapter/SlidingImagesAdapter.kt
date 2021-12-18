package com.example.movieappbms.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieappbms.models.Results
import com.example.movieappbms.uicontroller.FragmentSlidingImage

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */



class SlidingImagesAdapter(
    mContext: AppCompatActivity,
    private val items: ArrayList<Results>
) :
    FragmentStateAdapter(mContext) {
    var mNumOfTabs = items.size
    var fragment: Fragment? = null

    override fun getItemCount(): Int {
        return if (items.size > 0) {
            Int.MAX_VALUE
        } else {
            0
        }
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putInt("position", position % items.size)
        bundle.putSerializable("list", items)
        fragment = FragmentSlidingImage()
        fragment?.arguments = bundle
        return fragment!!
    }


}