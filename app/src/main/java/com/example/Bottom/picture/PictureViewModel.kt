package com.example.Bottom.picture

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import android.app.Activity
import android.content.Intent
import com.example.Bottom.ProfileActivity.ProfileActivity


@Suppress("DEPRECATION")
class PictureViewModel(application:Application): AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext



  fun back(){
      val intent= Intent(context,ProfileActivity::class.java)
      context.startActivity(intent)

  }



}