package com.example.cricketapp.models

data class PlayingBatsmanModel(
    var playerName: String,
    var score: Int,
    var ballsPlayed: Int,
    var fours : Int,
    var sixes : Int,
    var strikeRate : Float
)
