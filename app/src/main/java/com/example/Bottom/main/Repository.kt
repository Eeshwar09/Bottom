package com.example.Bottom.main

import androidx.databinding.BaseObservable
import java.io.Serializable


class Repository(): Serializable {


    var named: String = ""
    var mblno: String = ""
    var emailaddress: String = ""
    var photo_uri: String? = null
    var contactId: String = ""


}
class Title :BaseObservable(){

}