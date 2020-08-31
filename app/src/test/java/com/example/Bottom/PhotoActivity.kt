package com.example.Bottom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_photo.*
import android.graphics.Bitmap


class PhotoActivity : AppCompatActivity() {

    val b: Bitmap? = null


    private val database: WordRoomDatabase? = null

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        val ss: String = intent.getStringExtra("named")
        a2.setText(ss)
        val s: String = intent.getStringExtra("phoneNumber")
        a6.setText(s)
        val si: String = intent.getStringExtra("emailAddress")
        a4.setText(si)

        var position = intent.extras
        /** if (position!==null) {
        val image = position?.getInt("eeshwar")
        val email=position?.getInt("eesh")
        photo.setImageResource(image)
        textView2.setText(email)
        }**/
       // edittext_name.isEnabled = false
       // edittext_surname.isEnabled = false



       /** edittext_surname?.setText(surname)
        edittext_name.setText(named)
        edittext_schoolname.setText(school)
        edittext_grade.setText(grade)
        edittext_college.setText(college)
        edittext_percent.setText(percentge)
        edittext_university.setText(university)
        edittext_percentage.setText(percentage1)
        edittext_address.setText(address)
        edittext_hobbies.setText(hobbiess)
        edittext_contact.setText(contact_no)
        saved.setOnClickListener {


           // edittext_name.isEnabled = true
           // edittext_surname.isEnabled = true

           val named = edittext_name.text.toString().trim()
            val surname = edittext_surname.text.toString().trim()
            val school = edittext_schoolname.text.toString().trim()
            val grade = edittext_grade.text.toString().trim()
            val college = edittext_college.text.toString().trim()
            val percentage = edittext_percent.text.toString().trim()
            val university = edittext_university.text.toString().trim()
            val percentage1 = edittext_percentage.text.toString().trim()
            val address = edittext_address.text.toString().trim()
            val hobbiess = edittext_hobbies.text.toString().trim()
            val contact_no = edittext_contact.text.toString().trim()
            val names = edittext.text.toString().trim()
            val surnames = edittext2.text.toString().trim()
            val schools = edittext4.text.toString().trim()
            val grades = edittext6.text.toString().trim()
            val colleges = edittext8.text.toString().trim()
            val percentages = edittext10.text.toString().trim()
            val universitys = edittext12.text.toString().trim()
            val percentages1 = edittext14.text.toString().trim()
            val add = edittext16.text.toString().trim()
            val hobby = edittext20.text.toString().trim()
            val contacts = edittext22.text.toString().trim()
            val edt = pref.edit()
            edt.putString("named", named)
            edt.putString("email", surname)
            edt.putString("school_name", school)
            edt.putString("grade", grade)
            edt.putString("college_name", college)
            edt.putString("percentage", percentage)
            edt.putString("university_name", university)
            edt.putString("percentage1", percentage1)
            edt.putString("address", address)
            edt.putString("hobbiess", hobbiess)
            edt.putString("contact_no", contact_no)
            edt.putString("names", names)
            edt.putString("emails", surnames)
            edt.putString("schoolname", schools)
            edt.putString("grades", grades)
            edt.putString("colleges", colleges)
            edt.putString("percentages", percentages)
            edt.putString("univeristys", universitys)
            edt.putString("percentages1", percentages1)
            edt.putString("add", add)
            edt.putString("hobby", hobby)
            edt.putString("contact", contacts)
            edt.apply()**/
            // edt.remove("named")
            // edt.commit()



        }

    }



/**

val profileDetails = ProfileDetails()
profile_text?.text = profileDetails.name
profile_text1?.text = profileDetails.surname
profile_text2?.text = profileDetails.school
profile_text3?.text = profileDetails.grade
profile_text4?.text = profileDetails.college
profile_text5?.text = profileDetails.percentage
profile_text6?.text = profileDetails.university
profile_text7?.text = profileDetails.percentage1
profile_text8?.text = profileDetails.address
profile_text9?.text = profileDetails.hobbiess
profile_text10?.text = profileDetails.contact_no

textview1?.text = profileDetails.names
textview2?.text = profileDetails.surnames
textview3?.text = profileDetails.schools
textview4?.text = profileDetails.grades
textview5?.text = profileDetails.colleges
textview6?.text = profileDetails.percentages
textview7?.text = profileDetails.universitys
textview8?.text = profileDetails.percentages1
textview9?.text = profileDetails.add
textview10?.text = profileDetails.hobby
textview11?.text = profileDetails.contacts


//   val profileDetails = database?.profileDao()?.getUser(number.text.toString())
//database?.profileDao()?.insert(profileDetails)

//  val profileList = database?.profileDao()?.getAllProfiles()
//  database?.profileDao()?.insert(profileDetails)

val profileList = database!!.profileDao().getAllProfiles()


for (profile in profileList) {
Log.d(TAG, profile.names)
Log.d(TAG, profile.name)
Log.d(TAG, profile.surnames)
Log.d(TAG, profile.surname)
Log.d(TAG, profile.schools)
Log.d(TAG, profile.school)
Log.d(TAG, profile.grades)
Log.d(TAG, profile.grade)
Log.d(TAG, profile.colleges)
Log.d(TAG, profile.college)
Log.d(TAG, profile.percentages)
Log.d(TAG, profile.percentage)
Log.d(TAG, profile.universitys)
Log.d(TAG, profile.university)
Log.d(TAG, profile.percentages1)
Log.d(TAG, profile.percentage1)
Log.d(TAG, profile.add)
Log.d(TAG, profile.address)
Log.d(TAG, profile.hobby)
Log.d(TAG, profile.hobbiess)
Log.d(TAG, profile.contacts)
Log.d(TAG, profile.contact_no)



Log.d(TAG, "================================================")

}

 **/