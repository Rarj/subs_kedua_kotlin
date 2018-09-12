package dev.grack.matchschedulefootbal.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.model.Event
import dev.grack.matchschedulefootbal.model.Team
import dev.grack.matchschedulefootbal.model.TeamsResponse
import dev.grack.matchschedulefootbal.network.ApiClient
import dev.grack.matchschedulefootbal.network.ApiInterface
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var idhome: String
    private lateinit var idaway: String
    private lateinit var goalhome: String
    private lateinit var indexgoalhome: String
    private lateinit var gkhome: String
    private lateinit var defendHome: String
    private lateinit var midfieldHome: String
    private lateinit var forwardHome: String
    private lateinit var subsHome: String
    private lateinit var goalaway: String
    private lateinit var defendAway: String
    private lateinit var midfieldAway: String
    private lateinit var forwardAway: String
    private lateinit var subsAway: String
    private lateinit var gkaway: String

    companion object {
        val POSITIONEXTRA = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupDetail()
        loadGambar()
        loadGambarAway()

        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupDetail() {
        val detail = intent.getParcelableExtra<Event>(POSITIONEXTRA)

        if (detail.intHomeScore.equals(null)) scoreHome.text = "0"
        else scoreHome.text = detail.intHomeScore
        if (detail.intAwayScore.equals(null)) scoreAway.text = "0"
        else scoreAway.text = detail.intAwayScore

        eventMatch.text = detail.strEvent
        teamHome.text = detail.strHomeTeam
        teamHome.isSelected = true
        teamAway.text = detail.strAwayTeam
        teamAway.isSelected = true
        dateEvent.text = detail.dateEvent
        idhome = detail.idHomeTeam.toString()
        idaway = detail.idAwayTeam.toString()

        goalhome = detail.strHomeGoalDetails.toString()
        indexgoalhome = goalhome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeGoalDetails.equals(null) || detail.strHomeGoalDetails.equals("")) goal_home.text = "-"
        else goal_home.text = indexgoalhome.trim()

        gkhome = detail.strHomeLineupGoalkeeper.toString()
        indexgoalhome = gkhome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeLineupGoalkeeper.equals(null) || detail.strHomeLineupGoalkeeper.equals("")) goal_keeper_home.text = "-"
        else goal_keeper_home.text = indexgoalhome.trim()

        defendHome = detail.strHomeLineupDefense.toString()
        indexgoalhome = defendHome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeLineupDefense.equals(null) || detail.strHomeLineupDefense.equals("")) defend_home.text = "-"
        else defend_home.text = indexgoalhome.trim()

        midfieldHome = detail.strHomeLineupMidfield.toString()
        indexgoalhome = midfieldHome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeLineupMidfield.equals(null) || detail.strHomeLineupMidfield.equals("")) midfield_home.text = "-"
        else midfield_home.text = indexgoalhome.trim()

        forwardHome = detail.strHomeLineupForward.toString()
        indexgoalhome = forwardHome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeLineupForward.equals(null) || detail.strHomeLineupForward.equals("")) forward_home.text = "-"
        else forward_home.text = indexgoalhome.trim()

        subsHome = detail.strHomeLineupSubstitutes.toString()
        indexgoalhome = subsHome.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strHomeLineupSubstitutes.equals(null) || detail.strHomeLineupSubstitutes.equals("")) subs_home.text = "-"
        else subs_home.text = indexgoalhome.trim()

        goalaway = detail.strAwayGoalDetails.toString()
        indexgoalhome = goalaway.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayGoalDetails.equals(null) || detail.strAwayGoalDetails.equals("")) goal_away.text = "-"
        else goal_away.text = indexgoalhome.trim()

        gkaway = detail.strAwayLineupGoalkeeper.toString()
        indexgoalhome = gkaway.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayLineupGoalkeeper.equals(null) || detail.strAwayLineupGoalkeeper.equals("")) goal_keeper_away.text = "-"
        else goal_keeper_away.text = indexgoalhome.trim()

        defendAway = detail.strAwayLineupDefense.toString()
        indexgoalhome = defendAway.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayLineupDefense.equals(null) || detail.strAwayLineupDefense.equals("")) defense_away.text = "-"
        else defense_away.text = indexgoalhome.trim()

        midfieldAway = detail.strAwayLineupMidfield.toString()
        indexgoalhome = midfieldAway.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayLineupMidfield.equals(null) || detail.strAwayLineupMidfield.equals("")) midfield_away.text = "-"
        else midfield_away.text = indexgoalhome.trim()

        forwardAway = detail.strAwayLineupForward.toString()
        indexgoalhome = forwardAway.split(";").toString()
                .replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayLineupForward.equals(null) || detail.strAwayLineupForward.equals("")) forward_away.text = "-"
        else forward_away.text = indexgoalhome.trim()

        subsAway = detail.strAwayLineupSubstitutes.toString()
        indexgoalhome = subsAway.split(";").toString().replace("[", "").trim()
                .replace("]", "").trim()
                .replace(", ", ";\n").trim()
                .replace(",", ";").trim()
        if (detail.strAwayLineupSubstitutes.equals(null) || detail.strAwayLineupSubstitutes.equals("")) subs_away.text = "-"
        else subs_away.text = indexgoalhome.trim()

    }

    private fun loadGambarAway() {
        val apiSearch = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamAway(idaway)
        call.enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful()) {
                    val teams: List<Team> = response.body()?.teams!!
                    for (item: Team? in teams.iterator()) {
                        Picasso.get().load(item?.strTeamBadge).into(badgeAway)
                    }
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {}
        })
    }

    private fun loadGambar() {
        val apiSearch = ApiClient.getRetrofit().create(ApiInterface::class.java)
        val call = apiSearch.getIdTeamHome(idhome)
        call.enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                if (response.isSuccessful()) {
                    val teams: List<Team> = response.body()?.teams!!
                    for (item: Team? in teams.iterator()) {
                        Picasso.get().load(item?.strTeamBadge).into(badgeHome)
                    }
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {}
        })
    }
}
