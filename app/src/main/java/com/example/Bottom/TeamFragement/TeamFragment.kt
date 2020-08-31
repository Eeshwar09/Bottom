@file:Suppress("DEPRECATION")

package com.example.Bottom.TeamFragement

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout.VERTICAL
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.team_list.*
import com.google.firebase.database.DataSnapshot
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import com.example.Bottom.*
import com.example.Bottom.R
import com.google.gson.Gson


class TeamFragment : Fragment() {
    private var adapter: CustomAdapter? = null

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_team, container, false)

        setHasOptionsMenu(true)

        carddetails?.visibility = View.VISIBLE
        val mRecyclerView = view.findViewById(R.id.team_recyclerview) as RecyclerView

        val db = FirebaseDatabase.getInstance().getReference("data")


        val postListener = db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = ArrayList<Details>()

                for (snapshot in dataSnapshot.children) {
                    val arrayList: ArrayList<Details> = arrayListOf<Details>()
                    val post = snapshot.getValue(Details::class.java)
                    val gson = Gson()
                    val json: String = gson.toJson(post)
                    posts.add(post!!)


                }
                val activity = activity
                if (activity != null) {
                    adapter = CustomAdapter(requireActivity(), posts)
                    mRecyclerView.layoutManager = GridLayoutManager(activity, 2, VERTICAL, false)
                    mRecyclerView.adapter = adapter
                    adapter?.notifyDataSetChanged()

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        postListener
        db.addValueEventListener(postListener)

        return view

    }

    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater!!.inflate(R.menu.search, menu)
        val searchItem = menu!!.findItem(R.id.searching)
        val manager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchItem.actionView as SearchView
        searchView.setBackgroundColor(Color.TRANSPARENT)
        searchView.setSearchableInfo(manager.getSearchableInfo(activity!!.componentName))

        searchView.queryHint = "Search by Name"
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                val supportActionbar = (activity as AppCompatActivity).supportActionBar
                supportActionbar!!.setBackgroundDrawable(ColorDrawable(Color.rgb(0, 102, 255)))
                searchView.onActionViewCollapsed()
                searchView.isIconified = true
                return true
            }

            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                val supportActionbar = (activity as AppCompatActivity).supportActionBar
                supportActionbar!!.setBackgroundDrawable(ColorDrawable(Color.rgb(0, 102, 255)))
                searchView.isIconified = false
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                adapter?.filter?.filter(newText)
                adapter?.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter?.filter?.filter(query)
                searchView.clearFocus()
                searchView.setQuery("no matches found", false)
                return false
            }
        })

        return


    }

    @SuppressLint("WrongConstant")
    companion object {

        private val TAG = TeamFragment::class.java.simpleName
    }


}
