package com.example.Bottom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interns")
class Entity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "first_name")
    var Firstname: String? = null

    @ColumnInfo(name = "email")
    var email: String? = null

    @ColumnInfo(name = "contacts")
    var contact: Long = 0

}
