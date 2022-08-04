package com.example.jumpingmindstask.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jumpingmindstask.Adapters.TvShowAdapter
import com.example.jumpingmindstask.Constants
import com.example.jumpingmindstask.databinding.ActivityMovieDescriptionBinding
import com.example.jumpingmindstask.Repository.TvShowsRepository
import com.example.jumpingmindstask.Retrofit.TvShowApiInterface
import com.example.jumpingmindstask.Retrofit.RetrofitInstance
import com.example.jumpingmindstask.ViewModel.TvShowViewModelClass
import com.example.jumpingmindstask.ViewModel.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieDescriptionActivity : AppCompatActivity() {

    private lateinit var viewModel: TvShowViewModelClass
    private lateinit var tvShowsRepository: TvShowsRepository
    private lateinit var tvShowAdapter: TvShowAdapter

    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this,"Please make sure you are connected to the internet.", Toast.LENGTH_SHORT).show()

        val tvShowApiInterface: TvShowApiInterface = RetrofitInstance.getRetrofitInstance()
        tvShowsRepository = TvShowsRepository(tvShowApiInterface)

        tvShowAdapter = TvShowAdapter(this)
        binding.tvshowRv.layoutManager = LinearLayoutManager(this@MovieDescriptionActivity,LinearLayoutManager.VERTICAL,false)
        binding.tvshowRv.setHasFixedSize(true)

        viewModel = ViewModelProvider(this, ViewModelFactory(tvShowsRepository)).get(TvShowViewModelClass::class.java)

        binding.progressBar.visibility=View.VISIBLE
        binding.tvshowRv.visibility = View.GONE
        binding.networkerrortext.visibility=View.VISIBLE
        viewModel.tvShowLiveData.observe(this, Observer { tvshowList->
            tvShowAdapter.tvShows = tvshowList
            binding.progressBar.visibility=View.GONE
            binding.tvshowRv.visibility = View.VISIBLE
            binding.networkerrortext.visibility=View.GONE
        })
        binding.tvshowRv.adapter = tvShowAdapter
        binding.api.setOnClickListener {
            getApiLnkToIntent()
        }

        Linkify.addLinks(binding.api, Linkify.WEB_URLS)

    }

    fun getApiLnkToIntent() {
        val url:String = "https://www.tvmaze.com/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        startActivity(intent)
    }
}