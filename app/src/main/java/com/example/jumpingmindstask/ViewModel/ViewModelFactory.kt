package com.example.jumpingmindstask.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jumpingmindstask.Repository.TvShowsRepository

class ViewModelFactory(private val tvShowsRepository: TvShowsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TvShowViewModelClass(tvShowsRepository) as T
    }
}