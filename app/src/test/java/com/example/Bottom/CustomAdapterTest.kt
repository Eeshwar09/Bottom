package com.example.Bottom

import android.content.Context
import org.junit.Assert.*
import org.junit.Test

class CustomAdapterTest{
    private val context = mock(Context::class.java)

    @Test
    fun getItemCount() {
        val t= ArrayList<Details>()
        val userList = listOf(t)
        val testObject = CustomAdapter(context, t)
        assertEquals(1, testObject.itemCount)
    }

}