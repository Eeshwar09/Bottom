@file:Suppress("DEPRECATION")

package com.example.Bottom.main

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.Bottom.ProfileActivity.ProfileActivity
import com.example.Bottom.databinding.ActivityMainBinding
import android.view.WindowManager
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.MutableLiveData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.LiveData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ActivityCompat.startActivityForResult


class AuthViewModel(application: Application) : AndroidViewModel(application) {
    var authListner: Inteface? = null
    private val context = getApplication<Application>().applicationContext
    
    fun onfabClick() {
//
//
//        val pictureDialog = AlertDialog.Builder(context)
//
//        pictureDialog.setTitle("Select Action")
//        val alertDialogItems = arrayOf("Select Detail from contact", "Add details Manually")
//        pictureDialog.setPositiveButton("cancel") { dialog: DialogInterface?, id: Int ->
//            dialog!!.cancel()
//        }
//
//        pictureDialog.setItems(
//            alertDialogItems
//        ) { dialog, which ->
//            when (which) {
//                0 -> if (ContactsContract.Contacts.CONTENT_URI !== null) {
//                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
//                    val activity = context as Activity
//
//                       startActivityForResult(activity,)
//                    context.startActivity(intent)
//                } else
//                    Toast.makeText(context, "no contacts", Toast.LENGTH_LONG).show()
//                1 -> {
//                    val intent = Intent(context, ProfileActivity::class.java)
//
//                    context.startActivity(intent)
//                }
//
//            }
//        }
//        val alertDialog = pictureDialog.create()
//
//        alertDialog.getWindow()!!.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
//        alertDialog.show()
    }
    fun details(data: Intent?) {
        val contactData = data?.data
        val c = context.contentResolver.query(contactData!!, null, null, null, null)
        if (c!!.moveToFirst()) {
            var phoneNumber = ""
            var mblNo = ""
            var emailAddress = ""
            val named = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            val contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
            var photo_uri = c.getString(c.getColumnIndex(ContactsContract.Contacts.PHOTO_URI))
            var hasPhone =
                c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
            if (hasPhone.equals("1", ignoreCase = true))
                hasPhone = "true"
            else
                hasPhone = "false"
            if (java.lang.Boolean.parseBoolean(hasPhone)) {
                val phones = context.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,

                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null
                )
                while (phones!!.moveToNext()) {
                    phoneNumber =
                        phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    if (phoneNumber.startsWith("+")) {
                        if (phoneNumber.length == 13) {
                            mblNo = phoneNumber.substring(3)
                        }


                    } else if (phoneNumber.length == 10) {
                        mblNo = phoneNumber.substring(0)
                    }


                    photo_uri =
                        phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                }
                phones.close()
            }


            val emails = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                null,
                null
            )

            while (emails!!.moveToNext()) {
                emailAddress =
                    emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
            }
            emails.close()
            val intents = Intent(context, ProfileActivity::class.java)
            val parcel = Repository()

            parcel.contactId = contactId
            parcel.named = named
            parcel.emailaddress = emailAddress
            parcel.mblno = mblNo
            parcel.photo_uri = photo_uri


            intents.putExtra("named",named)
            intents.putExtra("contactId", contactId)
            intents.putExtra("phoneNumber", mblNo)
            intents.putExtra("emailAddress", emailAddress)
            intents.putExtra("photo_uri", photo_uri)
            context.startActivity(intents)
            Log.d("curs", "$named num$phoneNumber mail$emailAddress photo$photo_uri")
        }
        c.close()
    }

}


