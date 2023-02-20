package com.example.cricketapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cricketapp.data.Repo

class ScoringViewModelFactory(private val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoringViewModel::class.java)){
            return ScoringViewModel(repo) as T
        }
        throw IllegalArgumentException("Scoring ViewModel not found")
    }
}