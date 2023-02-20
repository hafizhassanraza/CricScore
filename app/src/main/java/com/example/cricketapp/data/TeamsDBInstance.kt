package com.example.cricketapp.data

import android.content.Context
import androidx.room.Room

object TeamsDBInstance {
    @Volatile
    private var teamsDBInstance: TeamsDatabase? = null
    fun getMoviesDBInstance(context: Context): TeamsDatabase{
        val existingTeamsDBInstance = teamsDBInstance
        if (existingTeamsDBInstance != null){
            return existingTeamsDBInstance
        }

        synchronized(this){
            return Room.databaseBuilder(
                context.applicationContext,
                TeamsDatabase::class.java,
                "teams_table"
            ).build()
        }
    }
}