package com.actia.tunisialeague.ui.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.actia.tunisialeague.R
import com.actia.tunisialeague.models.User
import com.actia.tunisialeague.ui.adapters.FavTeamsAdapter
import com.actia.tunisialeague.utils.EMAIL
import com.actia.tunisialeague.utils.FIRST_NAME
import com.actia.tunisialeague.utils.ID_USER
import com.actia.tunisialeague.utils.LAST_NAME
import com.actia.tunisialeague.utils.MyDataBase
import com.actia.tunisialeague.utils.PASSWORD
import com.actia.tunisialeague.utils.PREF_FILE_LOGIN
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    lateinit var imgProfile: ImageView
    lateinit var txtFullName: TextView
    lateinit var txtEmail: TextView
    lateinit var btnEdit: Button
    lateinit var btnLogout: Button
    lateinit var rvFav: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        imgProfile = rootView.findViewById(R.id.imgProfile)
        txtFullName = rootView.findViewById(R.id.txtFullName)
        txtEmail = rootView.findViewById(R.id.txtEmail)
        btnEdit = rootView.findViewById(R.id.btnEdit)
        btnLogout = rootView.findViewById(R.id.btnLogout)
        rvFav = rootView.findViewById(R.id.rvFav)

        val user: User
        requireActivity().getSharedPreferences(PREF_FILE_LOGIN, AppCompatActivity.MODE_PRIVATE).also {
            user = User(
                it.getInt(ID_USER, 0),
                it.getString(FIRST_NAME, "").toString() ,
                it.getString(LAST_NAME, "").toString() ,
                it.getString(EMAIL, "").toString() ,
                it. getString(PASSWORD, "").toString(),
            )
        }

        "${user.firstName} ${user.lastName}".also { txtFullName.text = it }

        txtEmail.text = user.email

        rvFav.adapter = FavTeamsAdapter(
            MyDataBase.getInstance(requireActivity()).teamDao().getAllTeams()
        )

        rvFav.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL ,false)

        btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle(getString(R.string.logoutTitle))
            builder.setMessage(R.string.logoutMessage)
            builder.setPositiveButton(getString(R.string.yes)){ dialogInterface, which ->
                requireActivity().getSharedPreferences(PREF_FILE_LOGIN, MODE_PRIVATE).edit().clear().apply()
                requireActivity().finish()
            }
            builder.setNegativeButton(getString(R.string.no)){ dialogInterface, which ->
                dialogInterface.dismiss()
            }
            builder.create().show()
        }

        btnEdit.setOnClickListener {
            Snackbar.make(requireActivity().findViewById(R.id.container), getString(R.string.coming_soon), Snackbar.LENGTH_SHORT)
                .setBackgroundTint(requireActivity().getColor(R.color.black))
                .show()
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        rvFav.adapter = FavTeamsAdapter(
            MyDataBase.getInstance(requireActivity()).teamDao().getAllTeams()
        )
    }
}
