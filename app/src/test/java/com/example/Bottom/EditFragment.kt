package com.example.Bottom

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import com.example.Bottom.ProfileFragement.ProfileFragment
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.fragment_edit.view.*


class EditFragment : Fragment() {

   private var Name = "name"
    private var SurName = "email"
    private var schoolname = "school"
    private var grade = "grade"
    private var collegename = "college"
    private var percentage = "percent"
    private var universityname = "university"
    private var percentage1 = "percent1"
    private var address = "address"
    private var hobbies = "hobbiess"
    private var contact = "contact_no"

    private lateinit var database : WordRoomDatabase
    private val TAG = EditFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(activity!!, WordRoomDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val pref = activity!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val view = inflater.inflate(R.layout.fragment_edit, container, false)

      var   WordRoomDatabase = WordRoomDatabase.getDatabase(this)!!
       val name = pref.getString("name", "")
        val surname = pref.getString("email", "")
       val profileDetails = ProfileDetails()
            view.profile_text.text = profileDetails.name
            view?.profile_text1?.text = profileDetails.surname
            view.profile_text2.text = profileDetails.school
            view.profile_text3.text = profileDetails.grade
            view.profile_text4.text = profileDetails.college
            view.profile_text5.text = profileDetails.percentage
            view.profile_text6.text = profileDetails.university
            view.profile_text7.text = profileDetails.percentage1
            view.profile_text8.text = profileDetails.address
            view.profile_text9.text = profileDetails.hobbiess
            view.profile_text10.text = profileDetails.contact_no

            view.textview1.text = profileDetails.names
            view.textview2.text = profileDetails.surnames
            view.textview3.text = profileDetails.schools
            view.textview4.text = profileDetails.grades
            view.textview5.text = profileDetails.colleges
            view.textview6.text = profileDetails.percentages
            view.textview7.text = profileDetails.universitys
            view.textview8.text = profileDetails.percentages1
            view.textview9.text = profileDetails.add
            view.textview10.text =profileDetails.hobby
            view.textview11.text =profileDetails.contacts



            view.submit.setOnClickListener() {


                val bundle = Bundle()//create a bundle
                 val name:String=view.edittext_name.text.toString().trim()
                val profileDetails = ProfileDetails()
                profileDetails.name = view.edittext_name.text.toString().trim()//get the string from edit text
                profileDetails.surname = view.edittext_surname.text.toString().trim()
                profileDetails.school = view.edittext_schoolname.text.toString().trim()
                profileDetails.grade = view.edittext_grade.text.toString().trim()
                profileDetails.college = view.edittext_college.text.toString().trim()
                profileDetails.percentage = view.edittext_percent.text.toString().trim()

                profileDetails.university = view.edittext_university.text.toString().trim()
                profileDetails.percentage1 = view.edittext_percentage.text.toString().trim()

                profileDetails.address = view.edittext_address.text.toString().trim()
                profileDetails.hobbiess = view.edittext_hobbies.text.toString().trim()
                profileDetails.contact_no = view.edittext_contact.text.toString().trim()

                profileDetails.names = view.edittext.text.toString().trim()
                profileDetails.surnames = view.edittext2.text.toString().trim()
                profileDetails.schools = view.edittext4.text.toString().trim()
                profileDetails.grades = view.edittext6.text.toString().trim()
                profileDetails.colleges = view.edittext8.text.toString().trim()
                profileDetails.percentages = view.edittext10.text.toString().trim()
                profileDetails.universitys = view.edittext12.text.toString().trim()
                profileDetails.percentages1 = view.edittext14.text.toString().trim()
                profileDetails.add = view.edittext16.text.toString().trim()
                profileDetails.hobby = view.edittext20.text.toString().trim()

                profileDetails.contacts = view.edittext22.text.toString().trim()


            // To endter data to database
            val entity = Entity()
            entity.email = ""
            entity.Firstname = ""
            entity.contact = 1234567890

          /**  database.Dao().insert(entity)

            database.profileDao().insert(profileDetails)

            val profileList = database.profileDao().getAllProfiles()

            for (profile in profileList){
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


       val edt = pref.edit()
            if (profileDetails.name.isEmpty() || profileDetails.surname.isEmpty() || profileDetails.school.isEmpty() ||
                profileDetails.grade.isEmpty() || profileDetails.school.isEmpty() || profileDetails.percentage.isEmpty()
                || profileDetails.university.isEmpty() || profileDetails.percentage1.isEmpty() || profileDetails.address.isEmpty()
            ) {
                Toast.makeText(getActivity(), "all fields are required", Toast.LENGTH_SHORT).show();
            } else {

               // edt.putString("name", name)
              //  edt.putString("email", surname)
                /**
                 *  val name = pref.getString("name", "")
                val surname = pref.getString("email", "")
                val school = pref.getString("school_name", "")
                val grade = pref.getString("grade", "")
                val college = pref.getString("college_name", "")
                val percentge = pref.getString("percentage", "")
                val university = pref.getString("university_name", "")
                val percentage1 = pref.getString("percentage1", "")
                val address = pref.getString("address", "")
                val hobbiess = pref.getString("hobbiess", "")
                val contact_no = pref.getString("contact_no", "")
                view.edittext_surname?.setText(surname)
                view.edittext_name.setText(name)
                view.edittext_schoolname.setText(school)
                view.edittext_grade.setText(grade)
                view.edittext_college.setText(college)
                view.edittext_percent.setText(percentge)
                view.edittext_university.setText(university)
                view.edittext_percentage.setText(percentage1)
                view.edittext_address.setText(address)
                view.edittext_hobbies.setText(hobbiess)
                view.edittext_contact.setText(contact_no)


                 *
                 *
                 *
                 * edt.putString("name", name)
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
                // edt.putString("emails", surnames)
                edt.putString("schoolname", schoolnames)
                edt.putString("grades", grades)
                edt.putString("colleges", colleges)
                edt.putString("percentages", percentages)
                edt.putString("univeristys", universitys)
                edt.putString("percentages1", percentages1)
                edt.putString("add", adds)
                edt.putString("hobby", hobby)
                edt.putString("contact", contacts)**/


              //  edt.commit()


               /* val fragment = ProfileFragment()//for swithching fragement
               // fragment.arguments = bundle
                switchToFragment(fragment)*/


            }
            //closefragment()**/

        }


        return view
    }

    
    private fun switchToFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentholder, fragment)?.commit()
    }

                }


class EditFragment : Fragment() {

   private var Name = "name"
    private var SurName = "email"
    private var schoolname = "school"
    private var grade = "grade"
    private var collegename = "college"
    private var percentage = "percent"
    private var universityname = "university"
    private var percentage1 = "percent1"
    private var address = "address"
    private var hobbies = "hobbiess"
    private var contact = "contact_no"

    private lateinit var database : WordRoomDatabase
    private val TAG = EditFragment::class.java.simpleName
    private var chapterDatabase: Database? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            activity!!,
            WordRoomDatabase::class.java,
            "task_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val pref = activity!!.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        val view = inflater.inflate(R.layout.fragment_edit, container, false)

     // var   WordRoomDatabase = WordRoomDatabase.getDatabase(this)!!
       val name = pref.getString("name", "")
        val surname = pref.getString("email", "")
       val profileDetails = ProfileDetails()

            view.submit.setOnClickListener() {


                val bundle = Bundle()//create a bundle
                //   val name:String=view.edittext_name.text.toString().trim()
                val profileDetails = ProfileDetails()
                profileDetails.name = view.edittext_namea.text.toString().trim()//get the string from edit text
                profileDetails.surname = view.edittext_surnamea.text.toString().trim()
                profileDetails.school = view.edittext_schoolnamea.text.toString().trim()
                profileDetails.grade = view.edittext_gradea.text.toString().trim()
                profileDetails.college = view.edittext_collegea.text.toString().trim()
                profileDetails.percentage = view.edittext_percenta.text.toString().trim()

                profileDetails.university = view.edittext_universitya.text.toString().trim()
                profileDetails.percentage1 = view.edittext_percentagea.text.toString().trim()

                profileDetails.address = view.edittext_addressa.text.toString().trim()
                profileDetails.hobbiess = view.edittext_hobbiesa.text.toString().trim()
                profileDetails.contact_no = view.edittext_contacta.text.toString().trim()

                profileDetails.names = view.edittexta.text.toString().trim()
                profileDetails.surnames = view.edittext2a.text.toString().trim()
                profileDetails.schools = view.edittext4a.text.toString().trim()
                profileDetails.grades = view.edittext6a.text.toString().trim()
                profileDetails.colleges = view.edittext8a.text.toString().trim()
                profileDetails.percentages = view.edittext10a.text.toString().trim()
                profileDetails.universitys = view.edittext12a.text.toString().trim()
                profileDetails.percentages1 = view.edittext14a.text.toString().trim()
                profileDetails.add = view.edittext16a.text.toString().trim()
                profileDetails.hobby = view.edittext20a.text.toString().trim()

                profileDetails.contacts = view.edittext22a.text.toString().trim()


            // To endter data to database
            val entity = Entity()
            entity.email = ""
            entity.Firstname = ""
            entity.contact = 1234567890

            database.Dao().insert(entity)

            database.profileDao().insert(profileDetails)

            val profileList = database.profileDao().getAllProfiles()

            for (profile in profileList){
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


       val edt = pref.edit()
            if (profileDetails.name.isEmpty() || profileDetails.surname.isEmpty() || profileDetails.school.isEmpty() ||
                profileDetails.grade.isEmpty() || profileDetails.school.isEmpty() || profileDetails.percentage.isEmpty()
                || profileDetails.university.isEmpty() || profileDetails.percentage1.isEmpty() || profileDetails.address.isEmpty()
            ) {
                Toast.makeText(
                    getActivity(),
                    "all fields are required",
                    Toast.LENGTH_SHORT
                ).show();
            } else {

               // edt.putString("name", name)
              //  edt.putString("email", surname)
                /**
                 *  val name = pref.getString("name", "")
                val surname = pref.getString("email", "")
                val school = pref.getString("school_name", "")
                val grade = pref.getString("grade", "")
                val college = pref.getString("college_name", "")
                val percentge = pref.getString("percentage", "")
                val university = pref.getString("university_name", "")
                val percentage1 = pref.getString("percentage1", "")
                val address = pref.getString("address", "")
                val hobbiess = pref.getString("hobbiess", "")
                val contact_no = pref.getString("contact_no", "")
                view.edittext_surname?.setText(surname)
                view.edittext_name.setText(name)
                view.edittext_schoolname.setText(school)
                view.edittext_grade.setText(grade)
                view.edittext_college.setText(college)
                view.edittext_percent.setText(percentge)
                view.edittext_university.setText(university)
                view.edittext_percentage.setText(percentage1)
                view.edittext_address.setText(address)
                view.edittext_hobbies.setText(hobbiess)
                view.edittext_contact.setText(contact_no)


                 *
                 *
                 *
                 * edt.putString("name", name)
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
                // edt.putString("emails", surnames)
                edt.putString("schoolname", schoolnames)
                edt.putString("grades", grades)
                edt.putString("colleges", colleges)
                edt.putString("percentages", percentages)
                edt.putString("univeristys", universitys)
                edt.putString("percentages1", percentages1)
                edt.putString("add", adds)
                edt.putString("hobby", hobby)
                edt.putString("contact", contacts)**/


              //  edt.commit()


              val fragment =
                    ProfileFragment()//for swithching fragement
               fragment.arguments = bundle
                switchToFragment(fragment)


            }
            //closefragment()

        }


        return view
    }


    private fun switchToFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentholder, fragment)?.commit()
    }

                }