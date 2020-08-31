package com.example.Bottom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): List<Entity>

    @Insert
    suspend fun insert(word: Entity)


    @Query("DELETE FROM word_table")
    fun deleteAll()
}