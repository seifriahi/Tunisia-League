package com.actia.tunisialeague.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.Team

const val PREF_FILE_LOGIN = "com.actia.tunisialeague.PREF_FILE_LOGIN"
const val ID_USER = "ID_USER"
const val FIRST_NAME = "FIRST_NAME"
const val LAST_NAME = "LAST_NAME"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBERED = "IS_REMEMBERED"
const val TEAM = "TEAM"

class MyStatics {

    companion object{
        val TEAMS = mutableListOf<Team>(
            Team(1,0, R.drawable.ic_est,"EST", 14,9,4,1),
            Team(2,0, R.drawable.ic_ess,"ESS", 14,9,2,3),
            Team(3,0, R.drawable.ic_cab,"CAB", 14,4,3,3),
            Team(4,0, R.drawable.ic_ust,"UST", 14,4,6,4),
            Team(5,0, R.drawable.ic_css,"CSS", 14,3,7,4),
            Team(6,0, R.drawable.ic_eshs,"ESHS", 14,3,2,5),
            Team(7,0, R.drawable.ic_st,"ST", 14,4,4,6),
            Team(8,0, R.drawable.ic_usbg,"USBG", 14,8,3,2),
            Team(9,0, R.drawable.ic_ob,"OB", 14,7,3,4),
            Team(10,0, R.drawable.ic_usm,"USM", 14,9,2,3),
            Team(11,0, R.drawable.ic_ass,"ASS", 14,4,2,4),
            Team(12,0, R.drawable.ic_ca,"CA", 14,7,3,4),
            Team(13,0, R.drawable.ic_eosb,"EOSB", 14,3,3,4),
            Team(14,0, R.drawable.ic_esm,"ESM", 14,5,4,5)
        )

        fun hideKeyboard(context: Context, view: View) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}