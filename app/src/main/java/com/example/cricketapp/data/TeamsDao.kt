package com.example.cricketapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cricketapp.models.TeamsModel

@Dao
interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTeam(movies: TeamsModel)

    @Query("SELECT * FROM teams_table")
    fun fetchTeams() : LiveData<List<TeamsModel>>
}