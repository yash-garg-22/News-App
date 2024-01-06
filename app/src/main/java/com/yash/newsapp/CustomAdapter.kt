import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yash.newsapp.R
import com.yash.newsapp.rv


class CustomAdapter(private val mList: List<rv.Data>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.author.text = ItemsViewModel.author.toString()
        holder.title.text = ItemsViewModel.title.toString()
        val ImageUrl = ItemsViewModel.urlToImage.toString()

        Glide.with(holder.itemView.context)
            .load(ImageUrl)
            .into(holder.image);

        holder.itemView.setOnClickListener {
            val url = "https://developers.android.com"
            val intent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(holder.itemView.context, Uri.parse(ItemsViewModel.url))
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val author: TextView = itemView.findViewById(R.id.author)
        val title: TextView = itemView.findViewById(R.id.title)
    }
}