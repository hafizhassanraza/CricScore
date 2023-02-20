package com.example.cricketapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cricketapp.models.CreateClubModel
import com.example.cricketapp.models.CreateTournamentModel
import com.example.cricketapp.models.PostModel
import com.example.cricketapp.models.TeamsModel
import com.example.cricketapp.network.RetrofitInstance

class Repo constructor(context: Context, url: String) {
    private val dao = TeamsDBInstance.getMoviesDBInstance(context).teamsDao()
    private val retrofit = RetrofitInstance.getInstance(url)

    fun readTeamFromDB() : LiveData<List<TeamsModel>> = dao.fetchTeams()

    suspend fun getAllTeams() = retrofit.getTeams()

    suspend fun getAllNews() = retrofit.getNewsFeeds()

    suspend fun getAllMatches() = retrofit.getMyMatches()

    suspend fun getAllTournaments() = retrofit.getMyTournaments()

    suspend fun getAllClubs() = retrofit.getClubs()


    suspend fun addTeamToDB(team: TeamsModel) = dao.addTeam(team)

    suspend fun addTeamToRetrofit(post: PostModel) = retrofit.postTeam(post)

    suspend fun addTournamentToRetrofit(tournament: CreateTournamentModel) = retrofit.postNewTournament(tournament)

    suspend fun addClubToRetrofit(club: CreateClubModel) = retrofit.postNewClub(club)

}