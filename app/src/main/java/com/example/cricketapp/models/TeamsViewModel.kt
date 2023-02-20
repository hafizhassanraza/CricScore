package com.example.cricketapp.models

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.cricketapp.data.Repo
import com.example.cricketapp.data.TeamsDBInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TeamsViewModel(private val repo: Repo) : ViewModel(){

    private var _teamsListLive = MutableLiveData<List<TeamsModel>>()
    val teamsListLive : LiveData<List<TeamsModel>> get() = _teamsListLive
//    private val repo: Repo
    init {
//        repo = Repo(application)
    fetchTeamsListViaCoroutines()
    }

    private fun fetchTeamsListViaCoroutines(){

        viewModelScope.launch(Dispatchers.IO){
            val response = try{
                repo.getAllTeams()
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                val fetchedMoviesList = response.body()!!
                _teamsListLive.postValue(fetchedMoviesList)
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }

    fun addTeamToRetrofit(team: PostModel){
        viewModelScope.launch(Dispatchers.IO){
            val postResponse = try {
                repo.addTeamToRetrofit(team)
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (postResponse.isSuccessful && postResponse.body() != null){
                val fetchedPost = postResponse.body()!!
                Log.d("Retrofit POST Response", "${postResponse.code()} $fetchedPost")
            }else{
                Log.e("Response Error", "Response not successful")
            }

        }
    }

    fun fetchTeams() : LiveData<List<TeamsModel>> = repo.readTeamFromDB()

    fun addTeam(teamList: List<TeamsModel>){
        viewModelScope.launch(Dispatchers.IO) {
            for (team in teamList){
                repo.addTeamToDB(team)
            }
        }
    }
}