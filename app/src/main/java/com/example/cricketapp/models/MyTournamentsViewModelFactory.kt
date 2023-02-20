package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class MyTournamentsViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyTournamentsViewModel::class.java)){
            return MyTournamentsViewModel(repo) as T
        }
        throw IllegalArgumentException("MyTournaments View Model not found")
    }
}