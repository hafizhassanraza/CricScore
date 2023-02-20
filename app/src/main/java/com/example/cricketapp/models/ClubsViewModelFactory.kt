package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class ClubsViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClubViewModel::class.java)){
            return ClubViewModel(repo) as T
        }
        throw IllegalArgumentException("Clubs View Model not found")
    }
}