@file:Suppress("DEPRECATION")

package com.example.Bottom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream

class BitmapUtils() {
    private lateinit var context: Context
    private val DECODE_BUFFER_SIZE = 16 * 1024

    constructor(context: Context) : this() {
        this.context = context

    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

    fun byteArrayToBase64(byteArray: ByteArray): String {
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun base64ToByteArray(base64String: String): ByteArray {
        return Base64.decode(base64String, Base64.DEFAULT);
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, DECODE_BUFFER_SIZE, byteArray.size)
    }

    fun uriToBitmap(uri: Uri): Bitmap {

        return MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }
}