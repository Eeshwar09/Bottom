package com.example.Bottom.picture

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.Bottom.R
import kotlinx.android.synthetic.main.activity_picture.*

class Picture : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        val images = intent.getStringExtra("picture")


        if (images != null) {
            val pictures = Uri.parse(images)
            Glide.with(this).load(pictures).into(picture)

        }
        backButtons.setOnClickListener {
            finish()
        }
    }

    }

