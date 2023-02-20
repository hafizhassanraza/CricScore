package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class CreateTournamentViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateTournamentViewModel::class.java)){
            return CreateTournamentViewModel(repo) as T
        }
        throw IllegalArgumentException("Create Tournament View Model not found")
    }
}
