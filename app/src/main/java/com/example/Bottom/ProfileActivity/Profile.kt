@file:Suppress("DEPRECATION")

package com.example.Bottom.ProfileActivity

import android.content.Context
import android.content.Intent
import android.widget.TextView

interface Profile {
    interface views {
        fun opengallery()
    }
    interface presenter{
        fun launchGallerys()
        fun contactDetails(){

        }
    }
    interface Model{
        fun getCounter(): Int
    }
}