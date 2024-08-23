package com.example.app06_retrofit.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app06_retrofit.databinding.PostItemBinding
import com.example.app06_retrofit.post.Post

class PostAdapter(val postList: MutableList<Post>): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    inner class PostHolder(val itemListView: PostItemBinding): RecyclerView.ViewHolder(itemListView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        val post = postList[position]

        holder.itemListView.idTextView.text = post.id.toString()
        holder.itemListView.titleTextView.text = post.title
        holder.itemListView.bodyTextView.text = post.body

    }

    override fun getItemCount(): Int {
        return postList.size
    }

}