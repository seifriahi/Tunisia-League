package com.actia.tunisialeague.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.Team
import com.actia.tunisialeague.ui.adapters.RankingAdapter
import com.actia.tunisialeague.utils.MyStatics

class RankingFragment : Fragment() {

    lateinit var recyclerRanking: RecyclerView
    lateinit var rankingAdapter: RankingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_ranking, container, false)

        recyclerRanking = rootView.findViewById(R.id.recyclerRanking)

        val teamList : MutableList<Team> = MyStatics.TEAMS

        teamList.sortBy { team -> (team.victory*3)+team.draws }
        teamList.reverse()

        rankingAdapter = RankingAdapter(teamList)

        recyclerRanking.adapter = rankingAdapter

        recyclerRanking.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL ,false)

        return rootView
    }


}
