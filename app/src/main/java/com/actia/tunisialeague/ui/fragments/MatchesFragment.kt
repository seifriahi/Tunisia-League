package com.actia.tunisialeague.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.Match
import com.actia.tunisialeague.ui.adapters.MatchesAdapter
import com.actia.tunisialeague.utils.MyStatics

class MatchesFragment : Fragment() {

    lateinit var rvMatches: RecyclerView
    lateinit var matchesAdapter: MatchesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_matches, container, false)

        rvMatches = rootView.findViewById(R.id.rvMatches)

        var matchesList : MutableList<Match> = ArrayList()
        matchesList.add(Match(1, MyStatics.TEAMS.get(0), MyStatics.TEAMS.get(1),"01/04/2023 - 15H00"))
        matchesList.add(Match(2, MyStatics.TEAMS.get(2), MyStatics.TEAMS.get(3),"01/04/2023 - 15H00"))
        matchesList.add(Match(3, MyStatics.TEAMS.get(4), MyStatics.TEAMS.get(5),"01/04/2023 - 15H00"))
        matchesList.add(Match(4, MyStatics.TEAMS.get(6), MyStatics.TEAMS.get(7),"01/04/2023 - 15H00"))
        matchesList.add(Match(5, MyStatics.TEAMS.get(8), MyStatics.TEAMS.get(9),"01/04/2023 - 15H00"))
        matchesList.add(Match(6, MyStatics.TEAMS.get(10), MyStatics.TEAMS.get(11),"01/04/2023 - 15H00"))
        matchesList.add(Match(7, MyStatics.TEAMS.get(12), MyStatics.TEAMS.get(13),"01/04/2023 - 15H00"))


        matchesAdapter = MatchesAdapter(matchesList)
        rvMatches.adapter = matchesAdapter
        rvMatches.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL ,false)

        return rootView
    }
}
