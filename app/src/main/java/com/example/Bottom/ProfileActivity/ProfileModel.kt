package com.example.Bottom.ProfileActivity

class ProfileModel:Profile.Model {
    private var PICK_IMAGE_REQUEST = 123
    override fun getCounter() = PICK_IMAGE_REQUEST
}