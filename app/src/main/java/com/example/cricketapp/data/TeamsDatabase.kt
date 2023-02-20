package com.example.cricketapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cricketapp.models.TeamsModel

@Database(entities = [TeamsModel::class], version = 1, exportSchema = false)
abstract class TeamsDatabase : RoomDatabase() {
    abstract fun teamsDao() : TeamsDao
}