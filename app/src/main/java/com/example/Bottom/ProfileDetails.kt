package com.example.Bottom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["contact_no"], unique = true)])
class ProfileDetails {
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "Email")
    var surname: String = ""
    @ColumnInfo(name = "school")
    var school: String = ""
    @ColumnInfo(name = "grade")
    var grade: String = ""
    @ColumnInfo(name = "college")
    var college: String = ""
    @ColumnInfo(name = "percentage")
    var percentage: String = ""
    @ColumnInfo(name = "university")
    var university: String = ""
    @ColumnInfo(name = "percentage1")
    var percentage1: String = ""
    @ColumnInfo(name = "address")
    var address: String = ""
    @ColumnInfo(name = "hobbiess")
    var hobbiess: String = ""

    @PrimaryKey
    @ColumnInfo(name = "contact_no")
    var contact_no: String = ""
    @ColumnInfo(name = "names")
    var names: String = ""
    @ColumnInfo(name = "surnames")
    var surnames: String = ""
    @ColumnInfo(name = "schools")
    var schools: String = ""
    @ColumnInfo(name = "grades")
    var grades: String = ""
    @ColumnInfo(name = "colleges")
    var colleges: String = ""
    @ColumnInfo(name = "percentages")
    var percentages: String = ""
    @ColumnInfo(name = "universitys")
    var universitys: String = ""
    @ColumnInfo(name = "percentages1")
    var percentages1: String = ""
    @ColumnInfo(name = "add")
    var add: String = ""
    @ColumnInfo(name = "hobby")
    var hobby: String = ""
    @ColumnInfo(name = "contacts")
    var contacts: String = ""
    @ColumnInfo(name = "images")
    var images: String = ""
    @ColumnInfo(name = "image")
    var image: String = ""


}