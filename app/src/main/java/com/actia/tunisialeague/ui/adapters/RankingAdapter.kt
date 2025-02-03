package com.actia.tunisialeague.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.Team
import com.actia.tunisialeague.ui.activities.TeamActivity
import com.actia.tunisialeague.utils.TEAM
import kotlin.math.sin

class RankingAdapter(val teams: MutableList<Team>) : RecyclerView.Adapter<RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val singleItem = teams.get(position)

        (position+1).toString().also { holder.teamRank.text = it }
        holder.teamImage.setImageResource(singleItem.image)
        holder.teamName.text = singleItem.name.toString()
        holder.teamMatches.text = singleItem.playedMatch.toString()
        holder.teamVictory.text = singleItem.victory.toString()
        holder.teamDraw.text = singleItem.draws.toString()
        holder.teamDefeat.text = singleItem.defeat.toString()
        ((singleItem.victory * 3)+ singleItem.draws).toString().also { holder.teamPoints.text = it }

        holder.teamImage.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, TeamActivity::class.java).apply {
                putExtra(TEAM, singleItem)
            })
        }
    }

    override fun getItemCount() = teams.size

}

class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val teamRank : TextView = itemView.findViewById<TextView>(R.id.teamRank)
    val teamImage : ImageView = itemView.findViewById<ImageView>(R.id.teamImage)
    val teamName : TextView = itemView.findViewById<TextView>(R.id.teamName)
    val teamMatches : TextView = itemView.findViewById<TextView>(R.id.teamMatches)
    val teamVictory : TextView = itemView.findViewById<TextView>(R.id.teamVictory)
    val teamDraw : TextView = itemView.findViewById<TextView>(R.id.teamDraw)
    val teamDefeat : TextView = itemView.findViewById<TextView>(R.id.teamDefeat)
    val teamPoints : TextView = itemView.findViewById<TextView>(R.id.teamPoints)
}