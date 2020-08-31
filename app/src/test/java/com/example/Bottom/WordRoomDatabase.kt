package com.example.Bottom

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = arrayOf(EditFragment::class), version = 1)
abstract class WordRoomDatabase:RoomDatabase() {


    abstract fun wordDao(): Dao


    companion object {
        @Volatile
        private var INSTANCE: com.example.Bottom.WordRoomDatabase? = null

        fun getDatabase(context: EditFragment) {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
               // return tempInstance
            }
        }
    }
}






