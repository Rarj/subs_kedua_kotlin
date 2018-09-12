package dev.grack.matchschedulefootbal.network

import dev.grack.matchschedulefootbal.BuildConfig
import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.EventsResponse
import dev.grack.matchschedulefootbal.model.Team
import dev.grack.matchschedulefootbal.model.TeamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<EventsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=4328")
    fun getPastMatch(): Call<EventsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamHome(@Query("id") id: String): Call<TeamsResponse>

    @GET("api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupteam.php/preview")
    fun getIdTeamAway(@Query("id") id: String): Call<TeamsResponse>
}