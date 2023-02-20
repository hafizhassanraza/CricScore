package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class TeamsViewModelFactory(private val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamsViewModel::class.java)){
            return TeamsViewModel(repo) as T
        }
        throw IllegalArgumentException("Teams ViewModel not found")
    }
}