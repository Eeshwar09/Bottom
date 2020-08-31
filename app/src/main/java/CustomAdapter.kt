import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.Bottom.R

class CustomAdapter (val context : Context, val userList: ArrayList<Quote>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //private lateinit var context: Context
    private var mObjects: ArrayList<Quote> = ArrayList<Quote>()


    // this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.team_list, parent, false)
        return ViewHolder(v)
    }
    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
     //   holder.bindItems(userList[position], position, context)
        //var item : Quote = userList[position]

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {

        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        fun bindItems(user: Quote, position: Int, context: Context) {
//            val imageView: ImageView = itemView.findViewById<ImageView>(R.id.cardimage)
//            val textViewName = itemView.findViewById(R.id.textViewUsername) as TextView//to hold the text
//            val textViewAddress = itemView.findViewById(R.id.textViewEmail) as TextView
//            val textViewAddres = itemView.findViewById(R.id.textedschool) as TextView
//
//
//            textViewName.text = user.name
//            textViewAddress.text = user.Email
//            textViewAddress.text=user.name
//            imageView.setImageResource(user.image)
//
//            itemView.setOnClickListener {
//                Log.d("RecyclerView", "$position Clicked")
//                val intent = Intent(context, PhotoActivity::class.java)
//                // intent.putExtra("image",user.image.(position))
//                intent.putExtra("eeshwar",user.image)
//                intent.putExtra("eesh",user.Email)
//
//                context.startActivity(intent)
//
//            }
//        }
    }

}