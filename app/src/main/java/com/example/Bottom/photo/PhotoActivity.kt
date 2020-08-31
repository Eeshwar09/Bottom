package com.example.Bottom.photo


import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.Bottom.R
import com.example.Bottom.picture.PictureViewModel
import kotlinx.android.synthetic.main.activity_picture.*


class PhotoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)


        val images = intent.getStringExtra("num")
        if (images != null) {
            val pictures = Uri.parse(images)
            Glide.with(this).load(pictures).into(picture)

        }
    }

}



