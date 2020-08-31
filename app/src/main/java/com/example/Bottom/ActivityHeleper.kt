package com.example.Bottom

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Activity.switchToFragment(newFrag: Fragment) {
    val manager = (this as AppCompatActivity).supportFragmentManager
    if (this.isFinishing){
        manager.beginTransaction().replace(R.id.fragmentholder, newFrag).commit()
    }else{
        manager.beginTransaction().replace(R.id.fragmentholder, newFrag).commitAllowingStateLoss()
    }
}