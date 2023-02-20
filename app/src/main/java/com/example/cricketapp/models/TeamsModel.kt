package com.example.cricketapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "teams_table")
data class TeamsModel(
    val category: String,
    val imageUrl: String,
    @PrimaryKey
    val name: String,
    val desc: String,
) : Parcelable
