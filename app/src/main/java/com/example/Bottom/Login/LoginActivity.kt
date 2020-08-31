@file:Suppress("DEPRECATION")

package com.example.Bottom.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.Bottom.Event
import com.example.Bottom.R
import com.example.Bottom.databinding.ActivityLoginBinding
import com.example.Bottom.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(),LoginInterface {


    private var mAuth: FirebaseAuth? = null
    private var presenter: LoginPresenter? = null


    companion object {

        const val myPreferences = "myPrefs"
        const val EMAIL = "email"
        private var EMPTY = ""
        private var userName:String= ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences(myPreferences, Context.MODE_PRIVATE)
        mAuth = FirebaseAuth.getInstance()
        presenter = LoginPresenter()
        if (sharedPreferences.getString(EMAIL, EMPTY) != EMPTY
        ) {
            val vm = ViewModelProviders.of(this)[LoginViewModel::class.java]

            //setContentView(R.layout.activity_main)
            val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
            binding.lifecycleOwner = this
            binding.loginViewModel = vm
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {

            val vm = ViewModelProviders.of(this)[LoginViewModel::class.java]
            val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
            binding.loginViewModel = vm
            binding.lifecycleOwner = this




            }

        }
    override fun authentication() {
        Toast.makeText(
            this@LoginActivity,
            "authentication is success",
            Toast.LENGTH_LONG
        ).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    }
