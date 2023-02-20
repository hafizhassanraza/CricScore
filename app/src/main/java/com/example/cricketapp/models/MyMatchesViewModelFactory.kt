package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class MyMatchesViewModelFactory(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyMatchesViewModel::class.java)){
            return MyMatchesViewModel(repo) as T
        }
        throw IllegalArgumentException("MyMatches View Model not found")
    }
}