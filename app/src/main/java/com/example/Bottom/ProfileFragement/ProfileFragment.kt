@file:Suppress("DEPRECATION")

package com.example.Bottom.ProfileFragement

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.Bottom.Login.LoginActivity
import com.example.Bottom.R


@Suppress("NAME_SHADOWING")
class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = inflater
        inflater!!.inflate(R.menu.power, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.itemId
        if (id == R.id.logouted) {
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("Are You Sure You Want Logout?")
                .setCancelable(false)
                .setPositiveButton("Proceed") { dialog, id ->
                    launchLoginActivity()

                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.cancel()
                }

            val alert = dialogBuilder.create()

            alert.setTitle("Logout")

            alert.show()
        }



        return super.onOptionsItemSelected(item)
    }

    private fun launchLoginActivity() {
        val sharedPreferences =
            activity!!.getSharedPreferences(LoginActivity.myPreferences, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        requireActivity().finish()

    }

}