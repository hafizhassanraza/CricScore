package com.example.cricketapp.models

data class PlayingMatchModel(
    var score: Int,
    var wickets: Int,
    var extras: Int,
    var overs: Float,
    var totalOvers: Int,
    var cRR: Float,
    var partnershipScore: Int,
    var partnershipBalls: Int
)
