package com.example.cricketapp.data

import com.example.cricketapp.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("movielist.json")
    suspend fun getTeams() : Response<List<TeamsModel>>

    @GET("movielist.json")
    suspend fun getNewsFeeds() : Response<List<NewsModel>>

    @GET("movielist.json")
    suspend fun getMyMatches() : Response<List<MyMatchesModel>>

    @GET("movielist.json")
    suspend fun getMyTournaments() : Response<List<MyTournamentsModel>>

    @GET("movielist.json")
    suspend fun getClubs() : Response<List<ClubModel>>



    @POST("posts")
    suspend fun postTeam(
        @Body addTeam: PostModel
    ) : Response<PostModel>

    @POST("posts")
    suspend fun postNewTournament(
        @Body addNewTournament: CreateTournamentModel
    ) : Response<PostModel>

    @POST("posts")
    suspend fun postNewClub(
        @Body addNewClub: CreateClubModel
    ) : Response<PostModel>
}