package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class CreateClubViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateClubViewModel::class.java)){
            return CreateClubViewModel(repo) as T
        }
        throw IllegalArgumentException("Create Club View Model not found")
    }

}
