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

class ClubViewModel(private val repo: Repo): ViewModel() {

    private var _clubsListLive = MutableLiveData<List<ClubModel>>()
    val clubsListLive : LiveData<List<ClubModel>>
        get() = _clubsListLive

    init {
        fetchClubsListViaCoroutines()
    }

    private fun fetchClubsListViaCoroutines(){

        viewModelScope.launch(Dispatchers.IO){
            val response = try{
                repo.getAllClubs()
            }catch (e: IOException){
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            }catch (e: HttpException){
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                val fetchedTournaments = response.body()!!
                _clubsListLive.postValue(fetchedTournaments)
            }else{
                Log.e("Response Error", "Response not successful")
            }
        }
    }
}