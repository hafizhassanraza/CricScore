package com.example.cricketapp.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketapp.data.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CreateClubViewModel(private val repo: Repo) : ViewModel(){


    fun addTournament(club: CreateClubModel){
        viewModelScope.launch(Dispatchers.IO){
            val postResponse = try {
                repo.addClubToRetrofit(club)
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (postResponse.isSuccessful && postResponse.body() != null){
                val fetchedClubs = postResponse.body()!!
                Log.d("Retrofit POST Response", "${postResponse.code()} $fetchedClubs")
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }

}
