package com.example.cricketapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cricketapp.data.Repo

class ScoringViewModel(private val repo: Repo) : ViewModel() {

    private var _batsmanOne = MutableLiveData<PlayingBatsmanModel>()
    val batsmanOne get() : LiveData<PlayingBatsmanModel> = _batsmanOne


    private var _batsmanTwo = MutableLiveData<PlayingBatsmanModel>()
    val batsmanTwo get() : LiveData<PlayingBatsmanModel> = _batsmanTwo

    private var _match = MutableLiveData<PlayingMatchModel>()
    val match get() : LiveData<PlayingMatchModel> = _match


    private var _bowler = MutableLiveData<PlayingBowlerModel>()
    val bowler get() : LiveData<PlayingBowlerModel> = _bowler

    fun updateBatsmanOne(
        name: String = "null",
        score: Int = 0,
        balls: Int = 0,
        four: Int = 0,
        six: Int = 0,
        strikeRate: Float = 0.0F
    ) {
        _batsmanOne.value = PlayingBatsmanModel(name, score, balls, four, six, strikeRate)
    }

    fun updateBatsmanTwo(
        name: String = "null",
        score: Int = 0,
        balls: Int = 0,
        four: Int = 0,
        six: Int = 0,
        strikeRate: Float = 0.0F
    ) {
        _batsmanTwo.value = PlayingBatsmanModel(name, score, balls, four, six, strikeRate)
    }

    fun updateBowler(
        overs: Float = 0.0F,
        maiden: Int = 0,
        runs: Int = 0,
        wickets: Int = 0,
        economy: Float = 0.0F
    ) {
        _bowler.value = PlayingBowlerModel(overs, maiden, runs, wickets, economy)
    }

    fun updateMatch(
        score: Int = 0,
        wickets: Int = 0,
        extras: Int = 0,
        overs: Float = 0.0F,
        totalOvers: Int = 0,
        cRR: Float = 0.0F,
        partnershipScore: Int = 0,
        partnershipBalls: Int = 0
    ) {
        _match.value = PlayingMatchModel(score, wickets, extras, overs, totalOvers, cRR, partnershipScore, partnershipBalls)
    }
}