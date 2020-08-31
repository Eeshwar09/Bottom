package com.example.Bottom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProfileDetails::class], version = 16,exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}



