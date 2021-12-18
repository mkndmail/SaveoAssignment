package com.example.movieappbms.uicontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.load
import com.example.movieappbms.BuildConfig
import com.example.movieappbms.R
import com.example.movieappbms.models.Results

/**
 * Created by Mukund Gururani, mkndmail@gmail.com on 12/18/2021
 */
class FragmentSlidingImage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.viewpager_item, container, false)
        val imageViewMain = view.findViewById<ImageView>(R.id.imageViewMain)
        val index = requireArguments().getInt("position")
        val listResults =
            requireArguments().getSerializable("list")
        if ((listResults as List<Results>).isNotEmpty()) {
            imageViewMain.load("${ BuildConfig.IMAGE_URL}${listResults[index].posterPath}")
        }
        return view
    }
}