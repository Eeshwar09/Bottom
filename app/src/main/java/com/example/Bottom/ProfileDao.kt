package com.example.Bottom

import androidx.room.*

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: ProfileDetails)

    @Query("SELECT DISTINCT * FROM users")
    fun getAllProfiles(): List<ProfileDetails>

    @Query("SELECT DISTINCT * FROM users WHERE contact_no = :contactNumber")
    fun getUser(contactNumber: String): ProfileDetails


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(customerDetails: List<ProfileDetails>?)


    @Delete
    fun delete(customerDetails: ProfileDetails)

    @Update
    fun update(customerDetails: ProfileDetails)

    @Query("UPDATE users SET name=:name, contact_no=:contactNo")
    fun updateProfileThroughQuery(name: String, contactNo: String)


}
