package com.example.cricketapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketapp.data.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MyTournamentsViewModel(private val repo: Repo) : ViewModel(){

    private var _tournamentsListLive = MutableLiveData<List<MyTournamentsModel>>()
    val tournamentsListLive : LiveData<List<MyTournamentsModel>>
        get() = _tournamentsListLive

    init {
        fetchTournamentsListViaCoroutines()
    }

    private fun fetchTournamentsListViaCoroutines(){

        viewModelScope.launch(Dispatchers.IO){
            val response = try{
                repo.getAllTournaments()
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                val fetchedTournaments = response.body()!!
                _tournamentsListLive.postValue(fetchedTournaments)
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }
}