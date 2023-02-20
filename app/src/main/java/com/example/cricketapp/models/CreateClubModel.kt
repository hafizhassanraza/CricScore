package com.example.cricketapp.models

data class CreateClubModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
