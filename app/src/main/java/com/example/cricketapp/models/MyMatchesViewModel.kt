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

class MyMatchesViewModel(private val repo: Repo) : ViewModel() {

    private val _myMatchesListLive = MutableLiveData<List<MyMatchesModel>>()
    val  myMatchesListLive : LiveData<List<MyMatchesModel>>
    get() = _myMatchesListLive

    init {
        fetchMyMatchesListViaCoroutines()
    }

    private fun fetchMyMatchesListViaCoroutines(){

        viewModelScope.launch(Dispatchers.IO){
            val response = try{
                repo.getAllMatches()
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                val fetchedMatches = response.body()!!
                _myMatchesListLive.postValue(fetchedMatches)
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }
}