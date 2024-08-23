package com.example.app06_retrofit.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app06_retrofit.R
import com.example.app06_retrofit.databinding.ItemListBinding

class PhotoAdapter(val photoList: MutableList<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    inner class PhotoHolder(val itemListView: ItemListBinding): RecyclerView.ViewHolder(itemListView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        return PhotoHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {

        Glide.with(holder.itemView)
            .load(photoList[position].thumbnailUrl)
            .into(holder.itemListView.imageView)

        holder.itemListView.imageView.setImageResource(R.drawable.ic_launcher_background)
        holder.itemListView.txId.text = photoList[position].id.toString()
        holder.itemListView.txTitle.text = photoList[position].title
        holder.itemListView.txUrl.text = photoList[position].url
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

}