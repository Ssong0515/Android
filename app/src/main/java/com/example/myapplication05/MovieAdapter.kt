package com.example.myapplication05

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication05.databinding.ThreeMovieImagesBinding

class ImgHolder(val threeMovieView: ThreeMovieImagesBinding) :
    RecyclerView.ViewHolder(threeMovieView.root)

class MovieAdapter(val imgs: MutableList<Int>) : RecyclerView.Adapter<ImgHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgHolder {
        val binding =
            ThreeMovieImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImgHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ImgHolder, position: Int) {
        holder.threeMovieView.img1.setImageResource(imgs.get(position))
    }
}