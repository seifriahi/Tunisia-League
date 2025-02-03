package com.actia.tunisialeague.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import com.actia.tunisialeague.R
import com.actia.tunisialeague.databinding.ActivityTeamBinding
import com.actia.tunisialeague.models.Team
import com.actia.tunisialeague.utils.MyDataBase
import com.actia.tunisialeague.utils.TEAM

class TeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamBinding
    lateinit var team: Team
    var btnFavModeAdd = true

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.title = getString(R.string.title_activity_team)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        team = intent.getSerializableExtra(TEAM) as Team

        binding.imgTeam.setImageResource(team!!.image)

        binding.txtVictory.text = getString(R.string.victory, team.victory)
        binding.txtDraw.text = getString(R.string.draw, team.draws)
        binding.txtDefeat.text = getString(R.string.defeat, team.defeat)
        binding.txtPoints.text = getString(R.string.totalPoints, ((team.victory * 3)+ team.draws))

        MyDataBase.getInstance(this).teamDao().getTeamById(team.id).also {
            btnFavModeAdd = it == null
        }

        if (btnFavModeAdd)
            binding.btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        else
            binding.btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)

        binding.btnFav.setOnClickListener {
            if (btnFavModeAdd)
                addFav()
            else
                deleteFav()
        }
    }

    fun addFav(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.insertFav))
        builder.setMessage(R.string.favAddMessage)
        builder.setPositiveButton(getString(R.string.yes)){ dialogInterface, which ->
            MyDataBase.getInstance(this).teamDao().insert(team)
            binding.btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
            btnFavModeAdd = false
        }
        builder.setNegativeButton(getString(R.string.no)){ dialogInterface, which ->
            dialogInterface.dismiss()
        }
        builder.create().show()

    }

    fun deleteFav(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.deleteFav))
        builder.setMessage(R.string.favDelMessage)
        builder.setPositiveButton(getString(R.string.yes)){ dialogInterface, which ->
            MyDataBase.getInstance(this).teamDao().delete(team)
            binding.btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            btnFavModeAdd = true
        }
        builder.setNegativeButton(getString(R.string.no)){ dialogInterface, which ->
            dialogInterface.dismiss()
        }
        builder.create().show()

    }
}