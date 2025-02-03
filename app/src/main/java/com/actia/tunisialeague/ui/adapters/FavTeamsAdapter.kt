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

class FavTeamsAdapter(val teams: MutableList<Team>) : RecyclerView.Adapter<FavTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team_fav, parent, false)
        return FavTeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTeamsViewHolder, position: Int) {
        val singleItem = teams[position]

        holder.imgTeam.setImageResource(singleItem.image)
        holder.txtTeam.text = singleItem.name

        holder.imgTeam.setOnClickListener {
            holder.itemView.context.startActivity(Intent(holder.itemView.context, TeamActivity::class.java).apply {
                putExtra(TEAM, singleItem)
            })
        }

    }

    override fun getItemCount() = teams.size

}

class FavTeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val imgTeam : ImageView = itemView.findViewById<ImageView>(R.id.imgTeam)
    val txtTeam : TextView = itemView.findViewById<TextView>(R.id.txtTeam)

}