@file:Suppress("DEPRECATION")

package com.example.Bottom

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.Bottom.ProfileActivity.ProfileActivity
import com.example.Bottom.databinding.TeamListBinding

class CustomAdapterViewModel(val dataBinding:TeamListBinding) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bindItems(user: Details, context: Context) {
        val er: LinearLayout = itemView.findViewById(R.id.carddetails) as LinearLayout
        er.visibility = View.GONE
        val hero = user
        val imageView = itemView.findViewById(R.id.cardimage) as ImageView
        val textViewName =
            itemView.findViewById(R.id.textViewUsername) as TextView//to hold the text
        val textViewEmail = itemView.findViewById(R.id.textViewEmail) as TextView
        val textedschool: TextView = itemView.findViewById(R.id.textedschool) as TextView
        val textedgrade: TextView = itemView.findViewById(R.id.textedgrade) as TextView
        val textedcollege: TextView = itemView.findViewById(R.id.textedcollege) as TextView
        val textedpercentage: TextView =
            itemView.findViewById(R.id.textedpercentage) as TextView
        val texteduniversity: TextView =
            itemView.findViewById(R.id.texteduniversity) as TextView
        val textedpercentage1: TextView =
            itemView.findViewById(R.id.textedpercentage1) as TextView
        val textedaddress: TextView = itemView.findViewById(R.id.textedaddress) as TextView
        val textedhobbiess: TextView = itemView.findViewById(R.id.textedhobbiess) as TextView
        val textNum = itemView.findViewById(R.id.textNum) as TextView



        textViewName.text = hero.name
        textViewEmail.text = user.email
        textedschool.text = user.school
        textedgrade.text = user.grade
        textedcollege.text = user.college
        textedpercentage.text = user.percentage
        texteduniversity.text = user.university
        textedpercentage1.text = user.percentage1
        textedaddress.text = user.address
        textedhobbiess.text = user.hobbies
        textNum.text = user.contact_no
        Glide.with(context).load(hero.image).into(imageView)


        dataBinding.root.setOnClickListener {
            Log.d("RecyclerView", "$position Clicked")
            val intents = Intent(context, ProfileActivity::class.java)
            intents.putExtra("eesh", user.name)
            intents.putExtra("mail", user.email)
            intents.putExtra("school", user.school)
            intents.putExtra("grade", user.grade)
            intents.putExtra("college", user.college)
            intents.putExtra("percentage", user.percentage)
            intents.putExtra("university", user.university)
            intents.putExtra("percentage1", user.percentage1)
            intents.putExtra("address", user.address)
            intents.putExtra("hobbiess", user.hobbies)
            intents.putExtra("num1", user.contact_no)
            intents.putExtra("num", hero.image)
            context.startActivity(intents)
        }

    }
}