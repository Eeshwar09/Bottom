package com.example.Bottom.Login

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.Bottom.Event
import com.example.Bottom.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val e = getApplication<Application>().applicationContext
    private var mAuth: FirebaseAuth? = null

    var userName: String = ""
    var password: String = ""
    private var _loginStatus = MutableLiveData<Event<Boolean>>()
    val loginStatus: MutableLiveData<Event<Boolean>>
        get() = _loginStatus



    private val handler = Handler()

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message
    val sharedPreferences = e.getSharedPreferences(LoginActivity.myPreferences, Context.MODE_PRIVATE)


    fun onLoginClicked() {
        mAuth = FirebaseAuth.getInstance()
       _progressVisible.value = true
        handler.postDelayed({
            _progressVisible.value = false
            mAuth!!.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(e,MainActivity::class.java)
                        e.startActivity(intent)
                        val authentication:LoginInterface?=null
                        authentication!!.authentication()


                    } else {
                        Toast.makeText(
                            e,
                            "authentication is fail",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }, 2000)
    }
     fun RegisterOnClick(){
         mAuth = FirebaseAuth.getInstance()

         mAuth!!.createUserWithEmailAndPassword(
           userName,password
         )

             .addOnCompleteListener {
                 if (it.isSuccessful) {
                     Toast.makeText(
                         e,
                         "authentication is success",
                         Toast.LENGTH_LONG
                     ).show()
                 } else {
                     Toast.makeText(
                         e,
                         "authentication is fail",
                         Toast.LENGTH_LONG
                     ).show()
                 }
             }


     }

}



