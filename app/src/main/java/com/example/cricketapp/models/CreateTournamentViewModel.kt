package com.example.cricketapp.models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketapp.data.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CreateTournamentViewModel(private val repo: Repo) : ViewModel() {


    fun addTournament(tournament: CreateTournamentModel){
        viewModelScope.launch(Dispatchers.IO){
            val postResponse = try {
                repo.addTournamentToRetrofit(tournament)
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (postResponse.isSuccessful && postResponse.body() != null){
                val fetchedTournament = postResponse.body()!!
                Log.d("Retrofit POST Response", "${postResponse.code()} $fetchedTournament")
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }
}