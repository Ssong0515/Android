package com.example.app06_retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app06_retrofit.databinding.ActivityMainBinding
import com.example.app06_retrofit.photo.Photo
import com.example.app06_retrofit.photo.PhotoAdapter
import com.example.app06_retrofit.post.Post
import com.example.app06_retrofit.post.PostAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnPhoto.setOnClickListener {
            var photoList = mutableListOf<Photo>()
            val photoAdapter = PhotoAdapter(photoList)
            binding.recyclerView.adapter = photoAdapter
            photoClient.retrofit.doGetPhotos().enqueue(object :retrofit2.Callback<List<Photo>>{
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful) {
                        Log.d("onResponse: ", "${response.body()}")
                        for (photo in response.body()!!){
                            photoList.add(photo)
                        }
                        photoAdapter.notifyDataSetChanged()
                    }

                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("onFailure: ", t.localizedMessage)
                }

            })
        }

        binding.btnPost.setOnClickListener {

            val postList = mutableListOf<Post>()
            val postAdapter = PostAdapter(postList)
            binding.recyclerView.adapter = postAdapter

            photoClient.retrofit.doGetPosts().enqueue(object :retrofit2.Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        Log.d("postOnResponse: ", "${response.body()}")
                        for (post in response.body()!!){
                            postList.add(post)
                        }
                        postAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Log.d("onFailure: ", t.localizedMessage)
                }

            })
        }

    }
}