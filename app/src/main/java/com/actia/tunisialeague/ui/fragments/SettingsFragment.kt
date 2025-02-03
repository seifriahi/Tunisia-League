package com.actia.tunisialeague.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.actia.tunisialeague.R

class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}
