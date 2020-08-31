@file:Suppress("DEPRECATION")

package com.example.Bottom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.Bottom.ProfileActivity.ProfileActivity
import com.example.Bottom.databinding.TeamListBinding


class CustomAdapter(val context: Context, internal var userList: List<Details>) :
    RecyclerView.Adapter<CustomAdapterViewModel>(),


    Filterable
{


    internal var filterListResult: List<Details>


    init {
        this.filterListResult = userList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(charString: CharSequence?): FilterResults {
                val charSearch: String = charString.toString()
                if (charSearch.isEmpty())
                    filterListResult = userList
                else {
                    val resultList = ArrayList<Details>()
                    for (item in userList) {
                        if (item.name.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(item)
                    }
                    filterListResult = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterListResult
                return filterResults

            }

            override fun publishResults(
                charsequence: CharSequence?,
                filterResults: FilterResults?
            ) {

                filterListResult = filterResults!!.values as List<Details>
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapterViewModel {
        val inflater = LayoutInflater.from(context)
        val dataBinding = TeamListBinding.inflate(inflater, parent, false)
        return CustomAdapterViewModel(dataBinding)

    }



    override fun onBindViewHolder(holder: CustomAdapterViewModel, position: Int) {
        val post = filterListResult[position]
       holder.bindItems(post, context)
    }


    override fun getItemCount()=
         filterListResult.size

}

