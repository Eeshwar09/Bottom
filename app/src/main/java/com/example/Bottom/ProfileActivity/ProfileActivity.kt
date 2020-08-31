@file:Suppress("DEPRECATION")

package com.example.Bottom.ProfileActivity

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.Bottom.Details
import com.example.Bottom.R
import com.example.Bottom.WordRoomDatabase
import com.example.Bottom.databinding.ActivityProfileBinding
import com.example.Bottom.picture.Picture
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.regex.Pattern


class ProfileActivity : AppCompatActivity(), Profile.views {
    private var filePath: Uri? = null
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null
    private var btn: Button? = null
    private var imageview: ImageView? = null
    lateinit var ref: DatabaseReference
    private lateinit var database: WordRoomDatabase
    private var pic: Uri? = null
    private val PICK_IMAGE_REQUEST = 123
    private val phone: ContactsContract.CommonDataKinds.Phone? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_profile)
        val viewModel = ViewModelProviders.of(this).get(ProfileActivityViewModel::class.java)
        val binding =
            DataBindingUtil.setContentView<ActivityProfileBinding>(this, R.layout.activity_profile)
        binding.profileActivityViewModel = viewModel

        btn = findViewById<View>(R.id.btn) as Button
        imageview = findViewById<View>(R.id.photos) as ImageView
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.getReference()
        btn?.setOnClickListener {

            launchGallery()
        }

//        eee.visibility = View.VISIBLE
        ee.visibility = View.GONE
        Edit.visibility = View.GONE
//        saving.visibility = View.VISIBLE
        Updating.visibility = View.GONE
        buttonOptions1.visibility = View.GONE
        Editing.visibility = View.GONE
        ref = FirebaseDatabase.getInstance().getReference("Details")
        //get and set the details from contact
        val ss: String? = intent.getStringExtra("named")
        val s: String? = intent.getStringExtra("phoneNumber")
        val si: String? = intent.getStringExtra("emailAddress")

        edittext_name.setText(ss)
        profilename.setText(ss)
        edittext_surname.setText(si)
        edittext_contact.setText(s)
        profile_text.setText(ss)
        profile_text1.setText(si)
        profile_text10.setText(s)
        val pics = intent.getStringExtra("photo_uri")
        if (pics != null) {
            val pic = Uri.parse(pics)
            //get and set photo from contact
            if (pic != null) {
                photos.setImageURI((pic))
                btn?.visibility = View.GONE
            }
        } else {
            btn?.visibility = View.VISIBLE
        }


        val position = intent.extras // get and set  the details from cardview
        val name = intent.getStringExtra("eesh")
        val mail = intent.getStringExtra("mail")
        val contact = intent.getStringExtra("num1")
        val school: String? = intent.getStringExtra("school")
        val grade: String? = intent.getStringExtra("grade")
        val college: String? = intent.getStringExtra("college")
        val percentage: String? = intent.getStringExtra("percentage")
        val university: String? = intent.getStringExtra("university")
        val percentage1: String? = intent.getStringExtra("percentage1")
        val address: String? = intent.getStringExtra("address")
        val hobbiess: String? = intent.getStringExtra("hobbiess")

        if (name !== null) {
            eee.visibility = View.GONE
            ee.visibility = View.VISIBLE
            saving.visibility = View.GONE
            Edit.visibility = View.GONE
            buttonOptions1.visibility = View.VISIBLE
            btn?.visibility = View.GONE
            Updating.visibility = View.GONE
            Editing.visibility = View.VISIBLE

            edittext_name.setText(name)
            profile_text.setText(name)
            profilename.setText(name)
            edittext_surname.setText(mail)
            profile_text1.setText(mail)
            edittext_contact.setText(contact)
            profile_text10.setText(contact)
            edittext_schoolname.setText(school)
            edittext_grade.setText(grade)
            edittext_college.setText(college)
            edittext_percentage.setText(percentage)
            edittext_university.setText(university)
            edittext_percent.setText(percentage1)
            edittext_address.setText(address)
            edittext_hobbies.setText(hobbiess)
            profile_text2.setText(school)
            profile_text3.setText(grade)
            profile_text4.setText(college)
            profile_text5.setText(percentage)
            profile_text6.setText(university)
            profile_text7.setText(percentage1)
            profile_text8.setText(address)
            profile_text9.setText(hobbiess)

            if (position != null) {
                val image = position.getString("num")
                if (image != null) {
                    Glide.with(this).load(image).into(photos)

                } else {
                    btn?.visibility = View.VISIBLE
                }
            }
        }

        Updating.setOnClickListener {
            UpdateDetails()
        }



        saving.setOnClickListener {
            saveDetails()
        }
        photos.setOnClickListener {

            val intents = Intent(this, Picture::class.java)
            val images = intent.getStringExtra("num")
            intents.putExtra("picture", images)
            startActivity(intents)
        }

        Edit.setOnClickListener {

            eee.visibility = View.VISIBLE
            ee.visibility = View.GONE
            Edit.visibility = View.GONE
            saving.visibility = View.VISIBLE
            Updating.visibility = View.GONE
        }
        backButton.setOnClickListener {
            finish()
        }
        Editing.setOnClickListener {
            eee.visibility = View.VISIBLE
            ee.visibility = View.GONE
            saving.visibility = View.VISIBLE
            Edit.visibility = View.GONE
            btn?.visibility = View.VISIBLE
            Updating.visibility = View.VISIBLE
            buttonOptions1.visibility = View.GONE
            edittext_contact.visibility = View.GONE
            textview11.visibility = View.GONE
            edittext22.visibility = View.GONE
            profile_text10.visibility = View.GONE
            Editing.visibility = View.GONE


        }


        buttonOptions1.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete -> {
                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setMessage(" Are you sure All data at this location will be permanently deleted?")
                            .setCancelable(false)
                            .setPositiveButton("proceed") { dialog: DialogInterface?, id: Int ->
                                showDelete()
                                finish()
                            }
                            .setNegativeButton("No") { dialog: DialogInterface?, id: Int ->
                                dialog!!.cancel()

                            }
                        val alertDialog = dialogBuilder.create()
                        alertDialog.setTitle("Delete")
                        alertDialog.show()
                        true
                    }

                    else -> false
                }
            }

            popupMenu.inflate(R.menu.card_nav)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }


    }

    override fun opengallery() {

        btn?.setOnClickListener {


        }

    }

    private fun UpdateDetails() {

        if (filePath !== null) {
            val progressDialog = ProgressDialog(this)
            val contact_no = edittext_contact.text.toString().trim()
            val Id = contact_no
            val imageReff = storageReference!!.child(Id)
            imageReff.putFile(filePath!!)
                .addOnSuccessListener {
                    it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->
                        progressDialog.setTitle("uploading...")
                        progressDialog.show()
                        val name = edittext_name.text.toString().trim()
                        val email = edittext_surname.text.toString().trim()
                        val school: String = edittext_schoolname.text.toString()
                        val grade = edittext_grade.text.toString().trim()
                        val college = edittext_college.text.toString().trim()
                        val percentage: String = edittext_percentage.text.toString()
                        val university = edittext_university.text.toString().trim()
                        val percentage1 = edittext_percent.text.toString().trim()
                        val address: String = edittext_address.text.toString()
                        val hobbies = edittext_hobbies.text.toString().trim()
                        val contact_no = edittext_contact.text.toString().trim()

                        if (filePath != null) {
                            val heroo = Details()
                            profile_text.setText(name)
                            profile_text1.setText(email)
                            profile_text2.setText(school)
                            profile_text3.setText(grade)
                            profile_text4.setText(college)
                            profile_text5.setText(percentage)
                            profile_text6.setText(university)
                            profile_text7.setText(percentage1)
                            profile_text8.setText(address)
                            profile_text9.setText(hobbies)
                            profile_text10.setText(contact_no)

                            if (name.isBlank() || email.isEmpty() ||
                                school.isEmpty() || grade.isEmpty() || college.isEmpty() || percentage.isEmpty() || university.isEmpty()
                                || percentage1.isEmpty() || address.isEmpty() || hobbies.isEmpty() || contact_no.isEmpty()
                            ) {
                                edittext_name.error = "please enter a name"
                                edittext_surname.error = "please enter a email"
                                edittext_schoolname.error = "please enter a school name"
                                edittext_grade.error = "please enter a grade"
                                edittext_college.error = "please enter a college name"
                                edittext_percentage.error = "please enter a percentage"
                                edittext_university.error = "please enter a university"
                                edittext_percent.error = "please enter a perentage"
                                edittext_address.error = "please enter a address"
                                edittext_hobbies.error = "please enter a hobbiess"
                                edittext_contact.error = "please enter a contact"
                                eee.visibility = View.VISIBLE
                                ee.visibility = View.GONE
                                saving.visibility = View.VISIBLE
                                Edit.visibility = View.GONE
                                bb.visibility = View.GONE
                                btn?.visibility = View.VISIBLE
                                Updating.visibility = View.VISIBLE

                            } else if (!validEmail(email)) {
                                edittext_surname.error = "enter valid Email"

                            } else if (!validPhoneNumber(edittext_contact.text.toString())) {
                                edittext_contact.error = "Phone number is not valid"

                            } else {
                                eee.visibility = View.GONE
                                ee.visibility = View.VISIBLE
                                saving.visibility = View.GONE
                                Edit.visibility = View.VISIBLE
                                bb.visibility = View.VISIBLE
                                btn?.visibility = View.GONE
                                Updating.visibility = View.GONE


                                val upload = Details(
                                    name,
                                    email,
                                    school,
                                    grade,
                                    college,
                                    percentage,
                                    university,
                                    percentage1,
                                    address,
                                    hobbies,
                                    contact_no,
                                    downloadUrl.toString()
                                )
                                val database = FirebaseDatabase.getInstance().getReference("data")
                                val uploadId = contact_no
                                database.child(uploadId).setValue(upload)
                                progressDialog.dismiss()
                                Toast.makeText(this, "Details updated", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }

                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "File not uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapShot ->
                    val progress =
                        100.0 * taskSnapShot.bytesTransferred / taskSnapShot.totalByteCount
                    progressDialog.setMessage("uploaded" + progress.toInt() + "%...")
                }
        } else {
            val images = intent.getStringExtra("num")
            pic = Uri.parse(images)
            if (pic != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("uploading...")
                progressDialog.show()
                val contact_nos = edittext_contact.text.toString().trim()
                val Id = contact_nos
                val imageReff = storageReference!!.child(Id)
                imageReff.putFile(pic!!)

                val name = edittext_name.text.toString().trim()
                val email = edittext_surname.text.toString().trim()
                val school: String = edittext_schoolname.text.toString()
                val grade = edittext_grade.text.toString().trim()
                val college = edittext_college.text.toString().trim()
                val percentage: String = edittext_percentage.text.toString()
                val university = edittext_university.text.toString().trim()
                val percentage1 = edittext_percent.text.toString().trim()
                val address: String = edittext_address.text.toString()
                val hobbies = edittext_hobbies.text.toString().trim()
                val contact_no = edittext_contact.text.toString().trim()

                if (pic != null) {
                    val heroo = Details()
                    profile_text.setText(name)
                    profile_text1.setText(email)
                    profile_text2.setText(school)
                    profile_text3.setText(grade)
                    profile_text4.setText(college)
                    profile_text5.setText(percentage)
                    profile_text6.setText(university)
                    profile_text7.setText(percentage1)
                    profile_text8.setText(address)
                    profile_text9.setText(hobbies)
                    profile_text10.setText(contact_no)

                    if (name.isBlank() || email.isEmpty() ||
                        school.isEmpty() || grade.isEmpty() || college.isEmpty() || percentage.isEmpty() || university.isEmpty()
                        || percentage1.isEmpty() || address.isEmpty() || hobbies.isEmpty() || contact_no.isEmpty()
                    ) {
                        edittext_name.error = "please enter a name"
                        edittext_surname.error = "please enter a email"
                        edittext_schoolname.error = "please enter a school name"
                        edittext_grade.error = "please enter a grade"
                        edittext_college.error = "please enter a college name"
                        edittext_percentage.error = "please enter a percentage"
                        edittext_university.error = "please enter a university"
                        edittext_percent.error = "please enter a perentage"
                        edittext_address.error = "please enter a address"
                        edittext_hobbies.error = "please enter a hobbiess"
                        edittext_contact.error = "please enter a contact"
                        eee.visibility = View.VISIBLE
                        ee.visibility = View.GONE
                        saving.visibility = View.VISIBLE
                        Edit.visibility = View.GONE
                        bb.visibility = View.GONE
                        btn?.visibility = View.VISIBLE
                        Updating.visibility = View.VISIBLE

                    } else if (!validEmail(email)) {
                        edittext_surname.error = "enter valid Email"

                    } else if (!validPhoneNumber(edittext_contact.text.toString())) {
                        edittext_contact.error = "Phone number is not valid"

                    } else {
                        eee.visibility = View.GONE
                        ee.visibility = View.VISIBLE
                        saving.visibility = View.GONE
                        Edit.visibility = View.GONE
                        bb.visibility = View.VISIBLE
                        btn?.visibility = View.GONE
                        Updating.visibility = View.VISIBLE
                        val upload = Details(
                            name,
                            email,
                            school,
                            grade,
                            college,
                            percentage,
                            university,
                            percentage1,
                            address,
                            hobbies,
                            contact_no,
                            images
                        )
                        val database = FirebaseDatabase.getInstance().getReference("data")
                        val uploadId = contact_no
                        database.child(uploadId).setValue(upload)
                        progressDialog.dismiss()
                        Toast.makeText(this, "Details updated", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun showDelete() {
        val dbHero = FirebaseDatabase.getInstance().getReference("data")
        val Id = edittext_contact.text.toString()
        dbHero.child(Id).removeValue()
        Log.d("", "deleted")

    }

    private fun validPhoneNumber(phone: String): Boolean {
        var check = false
        if (Pattern.matches("[0-9+]+", phone)) {
            if (phone.length < 10 || phone.length > 13) {
                check = false

            } else {
                check = true

            }
        } else {
            check = false
        }
        return check


    }

    private fun validEmail(email: String?): Boolean {
        email?.let {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        return false
    }


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {

                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                val viewModel = ProfileActivityViewModel(application)
                viewModel.saveImage(bitmap)
                photos.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private fun saveDetails() {
        val pics = intent.getStringExtra("photo_uri")
        if (pics != null) {
            val pic = Uri.parse(pics)
            if (pic != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("uploading...")
                progressDialog.show()
                val contact_no = edittext_contact.text.toString().trim()
                val Id = contact_no
                val imageReff = storageReference!!.child(Id)
                imageReff.putFile(pic)

                    .addOnSuccessListener {
                        it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->
                            val pics = downloadUrl
                            if (pic != null) {
                                val name = edittext_name.text.toString().trim()
                                val email = edittext_surname.text.toString().trim()
                                val school: String = edittext_schoolname.text.toString()
                                val grade = edittext_grade.text.toString().trim()
                                val college = edittext_college.text.toString().trim()
                                val percentage: String = edittext_percentage.text.toString()
                                val university = edittext_university.text.toString().trim()
                                val percentage1 = edittext_percent.text.toString().trim()
                                val address: String = edittext_address.text.toString()
                                val hobbies = edittext_hobbies.text.toString().trim()
                                val contact_no = edittext_contact.text.toString().trim()




                                if (name.isBlank() || email.isEmpty() ||
                                    school.isEmpty() || grade.isEmpty() || college.isEmpty() || percentage.isEmpty() || university.isEmpty()
                                    || percentage1.isEmpty() || address.isEmpty() || hobbies.isEmpty() || contact_no.isEmpty()
                                ) {
                                    edittext_name.error = "please enter a name"
                                    edittext_surname.error = "please enter a email"
                                    edittext_schoolname.error = "please enter a school name"
                                    edittext_grade.error = "please enter a grade"
                                    edittext_college.error = "please enter a college name"
                                    edittext_percentage.error = "please enter a percentage"
                                    edittext_university.error = "please enter a university"
                                    edittext_percent.error = "please enter a perentage"
                                    edittext_address.error = "please enter a address"
                                    edittext_hobbies.error = "please enter a hobbiess"
                                    edittext_contact.error = "please enter a contact"
                                    eee.visibility = View.VISIBLE
                                    ee.visibility = View.GONE
                                    saving.visibility = View.VISIBLE
                                    Edit.visibility = View.GONE
                                    bb.visibility = View.GONE
                                    btn?.visibility = View.VISIBLE
                                    Updating.visibility = View.VISIBLE

                                } else if (!validEmail(email)) {
                                    edittext_surname.error = "enter valid Email"

                                } else if (!validPhoneNumber(edittext_contact.text.toString())) {
                                    edittext_contact.error = "Phone number is not valid"

                                } else {
                                    val heroo = Details()
                                    profile_text.setText(name)
                                    profile_text1.setText(email)
                                    profile_text2.setText(school)
                                    profile_text3.setText(grade)
                                    profile_text4.setText(college)
                                    profile_text5.setText(percentage)
                                    profile_text6.setText(university)
                                    profile_text7.setText(percentage1)
                                    profile_text8.setText(address)
                                    profile_text9.setText(hobbies)
                                    profile_text10.setText(contact_no)
                                    eee.visibility = View.GONE
                                    ee.visibility = View.VISIBLE
                                    saving.visibility = View.GONE
                                    Edit.visibility = View.VISIBLE
                                    bb.visibility = View.VISIBLE
                                    btn?.visibility = View.GONE
                                    Updating.visibility = View.GONE


                                    val upload = Details(
                                        name,
                                        email,
                                        school,
                                        grade,
                                        college,
                                        percentage,
                                        university,
                                        percentage1,
                                        address,
                                        hobbies,
                                        contact_no,
                                        downloadUrl.toString()
                                    )
                                    val database =
                                        FirebaseDatabase.getInstance().getReference("data")
                                    val uploadId = contact_no
                                    database.child(uploadId).setValue(upload)
                                    progressDialog.dismiss()
                                    Toast.makeText(this, "Details uploaded", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                        }

                    }
                    .addOnFailureListener {
                        progressDialog.dismiss()
                        Toast.makeText(this, "File not uploaded", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { taskSnapShot ->
                        val progress =
                            100.0 * taskSnapShot.bytesTransferred / taskSnapShot.totalByteCount
                        progressDialog.setMessage("uploaded" + progress.toInt() + "%...")
                    }
            }
        } else if (pic == null) {
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("uploading...")
            progressDialog.show()
            val contact_no = edittext_contact.text.toString().trim()
            val Id = contact_no
            val imageReff = storageReference!!.child(Id)
            imageReff.putFile(filePath!!)
                .addOnSuccessListener {
                    it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->
                        val pics = downloadUrl
                        val name = edittext_name.text.toString().trim()
                        val email = edittext_surname.text.toString().trim()
                        val school: String = edittext_schoolname.text.toString()
                        val grade = edittext_grade.text.toString().trim()
                        val college = edittext_college.text.toString().trim()
                        val percentage: String = edittext_percentage.text.toString()
                        val university = edittext_university.text.toString().trim()
                        val percentage1 = edittext_percent.text.toString().trim()
                        val address: String = edittext_address.text.toString()
                        val hobbies = edittext_hobbies.text.toString().trim()
                        val contact_no = edittext_contact.text.toString().trim()

                        if (filePath != null) {
                            val heroo = Details()
                            profile_text.setText(name)
                            profile_text1.setText(email)
                            profile_text2.setText(school)
                            profile_text3.setText(grade)
                            profile_text4.setText(college)
                            profile_text5.setText(percentage)
                            profile_text6.setText(university)
                            profile_text7.setText(percentage1)
                            profile_text8.setText(address)
                            profile_text9.setText(hobbies)
                            profile_text10.setText(contact_no)

                            if (name.isBlank() || email.isEmpty() ||
                                school.isEmpty() || grade.isEmpty() || college.isEmpty() || percentage.isEmpty() || university.isEmpty()
                                || percentage1.isEmpty() || address.isEmpty() || hobbies.isEmpty() || contact_no.isEmpty()
                            ) {
                                edittext_name.error = "please enter a name"
                                edittext_surname.error = "please enter a email"
                                edittext_schoolname.error = "please enter a school name"
                                edittext_grade.error = "please enter a grade"
                                edittext_college.error = "please enter a college name"
                                edittext_percentage.error = "please enter a percentage"
                                edittext_university.error = "please enter a university"
                                edittext_percent.error = "please enter a perentage"
                                edittext_address.error = "please enter a address"
                                edittext_hobbies.error = "please enter a hobbiess"
                                edittext_contact.error = "please enter a contact"
                                eee.visibility = View.VISIBLE
                                ee.visibility = View.GONE
                                saving.visibility = View.VISIBLE
                                Edit.visibility = View.GONE
                                bb.visibility = View.GONE
                                btn?.visibility = View.VISIBLE
                                Updating.visibility = View.VISIBLE

                            } else if (!validEmail(email)) {
                                edittext_surname.error = "enter valid Email"

                            } else if (!validPhoneNumber(edittext_contact.text.toString())) {
                                edittext_contact.error = "Phone number is not valid"

                            } else {
                                eee.visibility = View.GONE
                                ee.visibility = View.VISIBLE
                                saving.visibility = View.GONE
                                Edit.visibility = View.VISIBLE
                                bb.visibility = View.VISIBLE
                                btn?.visibility = View.GONE
                                Updating.visibility = View.GONE


                                val upload = Details(
                                    name,
                                    email,
                                    school,
                                    grade,
                                    college,
                                    percentage,
                                    university,
                                    percentage1,
                                    address,
                                    hobbies,
                                    contact_no,
                                    downloadUrl.toString()
                                )
                                val database = FirebaseDatabase.getInstance().getReference("data")
                                val uploadId = contact_no
                                database.child(uploadId).setValue(upload)
                                progressDialog.dismiss()
                                Toast.makeText(this, "Details uploaded", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "File not uploaded", Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { taskSnapShot ->
                    val progress =
                        100.0 * taskSnapShot.bytesTransferred / taskSnapShot.totalByteCount
                    progressDialog.setMessage("uploaded" + progress.toInt() + "%...")
                }
        } else {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_LONG).show()
        }

    }

}