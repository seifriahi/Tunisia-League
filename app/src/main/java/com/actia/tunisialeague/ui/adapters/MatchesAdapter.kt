package com.actia.tunisialeague.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.Match
import com.actia.tunisialeague.ui.activities.TeamActivity
import com.actia.tunisialeague.utils.TEAM

class MatchesAdapter(val matches: MutableList<Match>) : RecyclerView.Adapter<MatchesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_matches, parent, false)
        return MatchesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val singleItem = matches.get(position)

        holder.imTeam1.setImageResource(singleItem.team1.image)
        holder.imTeam2.setImageResource(singleItem.team2.image)

        holder.txTeam1.text = singleItem.team1.name
        holder.txTeam2.text = singleItem.team2.name

        holder.matchesDate.text = singleItem.date

        holder.imTeam1.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, TeamActivity::class.java).apply {
                putExtra(TEAM, singleItem.team1)
            })
        }

        holder.imTeam2.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, TeamActivity::class.java).apply {
                putExtra(TEAM, singleItem.team2)
            })
        }
    }

    override fun getItemCount() = matches.size

}

class MatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val imTeam1 : ImageView = itemView.findViewById<ImageView>(R.id.imgTeam1)
    val txTeam1 : TextView = itemView.findViewById<TextView>(R.id.txtTeam1)
    val imTeam2 : ImageView = itemView.findViewById<ImageView>(R.id.imgTeam2)
    val txTeam2 : TextView = itemView.findViewById<TextView>(R.id.txtTeam2)
    val matchesDate : TextView = itemView.findViewById<TextView>(R.id.txtDate)
}