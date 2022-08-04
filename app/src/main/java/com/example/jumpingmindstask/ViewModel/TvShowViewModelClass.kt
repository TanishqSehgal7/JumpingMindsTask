package com.example.jumpingmindstask.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingmindstask.Model.TvShowsModelClassItem
import com.example.jumpingmindstask.Repository.TvShowsRepository
import kotlinx.coroutines.launch

class TvShowViewModelClass(private val tvShowsRepository: TvShowsRepository) : ViewModel() {

    private val tvShowResponse = MutableLiveData<List<TvShowsModelClassItem>>()
    val tvShowLiveData: LiveData<List<TvShowsModelClassItem>>
    get() = tvShowResponse

    init {
        fetchAllTvShowData()
    }

    private fun fetchAllTvShowData() = viewModelScope.launch {
        tvShowsRepository.getTvShows().let { response ->  
            if (response.isSuccessful) {
                tvShowResponse.postValue(response.body())
            } else {
               Log.d("ErrorTvshow", "Error: ${response.code()}")
            }
        }
    }
}