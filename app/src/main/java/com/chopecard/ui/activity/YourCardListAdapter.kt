import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.chopecard.R
import com.chopecard.data.model.Card
import com.chopecard.ui.activity.ImageActivity
import java.io.IOException

class YourCardListAdapter(private val cardList: List<Card>, private val context: Context) :
    RecyclerView.Adapter<YourCardListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cardList[position]
        holder.tvName.text = currentCard.name
        holder.tvDescription.text = currentCard.desc

        val imageUrl = "https://images.ygoprodeck.com/images/cards/78121572.jpg"

        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("image_url", imageUrl)
            //startActivity(intent)
        }

        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}
