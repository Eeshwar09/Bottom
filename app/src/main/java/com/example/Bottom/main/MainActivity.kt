@file:Suppress("DEPRECATION")

package com.example.Bottom.main

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.Bottom.ProfileActivity.ProfileActivity
import com.example.Bottom.ProfileFragement.ProfileFragment
import com.example.Bottom.R
import com.example.Bottom.TeamFragement.TeamFragment
import com.example.Bottom.databinding.ActivityMainBinding
import com.example.Bottom.switchToFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*


class MainActivity : AppCompatActivity(), Inteface {


    private val manager = supportFragmentManager
    @SuppressLint("RestrictedApi", "SetTextI18n")
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {


                    switchToFragment(ProfileFragment())
                    fab?.visibility = View.GONE
                    fab?.visibility = View.GONE
                    toolbartext.setText("AboutMe")
                    profile_text7
                    return@OnNavigationItemSelectedListener true
                }
                R.id.team -> {
                    fab?.visibility = View.VISIBLE


                    switchToFragment(TeamFragment())
                    toolbartext.setText("Team")
                    return@OnNavigationItemSelectedListener true
                }

            }
            false

        }

    @SuppressLint("RestrictedApi", "WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        viewModel.authListner = this
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.authView = viewModel
        setSupportActionBar(binding.toolbar)
        fab?.visibility = View.GONE
        toolbartext.setText("AboutMe")

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(false)

        actionBar.setDisplayShowTitleEnabled(false)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        switchToFragment(ProfileFragment())
        fab.setOnClickListener {
            showDialog()
        }


    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            val viewmodel = AuthViewModel(application)
            viewmodel.details(data)

        }
    }

    private fun showDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select Detail from contact", "Add details Manually")
        pictureDialog.setPositiveButton("cancel") { dialog: DialogInterface?, id: Int ->
            dialog!!.cancel()
        }
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> if (ContactsContract.Contacts.CONTENT_URI !== null) {
                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    startActivityForResult(intent, 1)
                } else
                    Toast.makeText(this, "no contacts", Toast.LENGTH_LONG).show()
                1 -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }


            }
        }
        pictureDialog.show()
    }
}


