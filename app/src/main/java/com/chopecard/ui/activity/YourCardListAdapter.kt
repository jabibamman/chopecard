
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chopecard.R
import com.chopecard.domain.models.Product
import com.chopecard.ui.activity.ImageActivity

class YourCardListAdapter(private val cardList: MutableList<Product>, private val context: Context) :
    RecyclerView.Adapter<YourCardListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.body_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cardList[position]
        holder.tvName.text = currentCard.name
        holder.tvDescription.text = currentCard.description

        Glide.with(context)
            .load(currentCard.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("image_url", currentCard.imageUrl)
            context.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(products: List<Product>) {
        cardList.clear()
        cardList.addAll(products)
        notifyDataSetChanged()
    }
}
