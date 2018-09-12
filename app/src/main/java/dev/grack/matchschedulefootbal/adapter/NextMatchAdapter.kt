package dev.grack.matchschedulefootbal.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.grack.matchschedulefootbal.R
import dev.grack.matchschedulefootbal.model.Event
import org.jetbrains.anko.find

class NextMatchAdapter(private var nextmatchs: MutableList<Event>, var listener: (Event) -> Unit) :
        RecyclerView.Adapter<NextMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount() = nextmatchs.size

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(nextmatchs[position], listener)
    }
}

class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.find(R.id.team_home)
    private val teamAway: TextView = view.find(R.id.team_away)
    private val date: TextView = view.find(R.id.date_event)
    private val homeScore: TextView = view.findViewById(R.id.home_score)
    private val awayScore: TextView = view.findViewById(R.id.away_score)
    private lateinit var idHome: String
    private lateinit var idAway: String
    private lateinit var matchEvent: String

    fun bindItem(nextMatch: Event, listener: (Event) -> Unit) {
        teamHome.text = nextMatch.strHomeTeam
        teamAway.text = nextMatch.strAwayTeam
        date.text = nextMatch.dateEvent
        idHome = nextMatch.idHomeTeam!!
        idAway = nextMatch.idAwayTeam!!
        matchEvent = nextMatch.strEvent!!

        if (nextMatch.intHomeScore.equals(null)) homeScore.text = "0"
        else homeScore.text = nextMatch.intHomeScore
        if (nextMatch.intAwayScore.equals(null)) awayScore.text = "0"
        else awayScore.text = nextMatch.intAwayScore

        itemView.setOnClickListener {
            listener(nextMatch)
        }
    }
}
