package com.example.Bottom.TeamFragement

import android.content.Context
import android.telecom.Call
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.Bottom.Details
import com.example.Bottom.R
import com.example.Bottom.databinding.TeamListBinding

class TeamViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun Imageclick(context: Context, postModel: Details, position: Int){

        val textViewEmail = itemView.findViewById(R.id.textViewEmail) as TextView
        textViewEmail.text = postModel.name



    }






}