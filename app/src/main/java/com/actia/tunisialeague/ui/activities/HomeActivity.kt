package com.actia.tunisialeague.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.actia.tunisialeague.R
import com.actia.tunisialeague.ui.fragments.MatchesFragment
import com.actia.tunisialeague.ui.fragments.ProfileFragment
import com.actia.tunisialeague.ui.fragments.RankingFragment
import com.actia.tunisialeague.ui.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)

        val btmNavBar: BottomNavigationView = findViewById(R.id.bottomNavBar)

        btmNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_dashboard -> changeFragment(MatchesFragment(), "")
                R.id.navigation_ranking -> changeFragment(RankingFragment(), "")
                R.id.navigation_profile -> changeFragment(ProfileFragment(), "")
                R.id.navigation_settings -> changeFragment(SettingsFragment(), "")
            }
            return@setOnItemSelectedListener true
        }

        changeFragment(MatchesFragment(), "")
    }

    fun changeFragment(fragment: Fragment, tag: String){
        if (tag.isEmpty()){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }else{
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(tag)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.toolbar_notification -> {
                Snackbar.make(findViewById(R.id.container), getString(R.string.this_is_for_notifications),Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(getColor(R.color.black))
                    .show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}