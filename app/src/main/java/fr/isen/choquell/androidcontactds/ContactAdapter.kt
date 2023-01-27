import activity.Contact.Api.Results
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.choquell.androidcontactds.R

internal class ContactAdapter(private var itemlist: ArrayList<Results>, val onItemClickListener: (Results) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    internal class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemtextview)
        var mailTextView: TextView = view.findViewById(R.id.emailView)
        var addresseTextView: TextView = view.findViewById(R.id.adresseView)
        var image: ImageView = view.findViewById(R.id.imageView)



    }



    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemlist[position]
        holder.itemTextView.text = item.name?.first +" "+ item.name?.last
        holder.mailTextView.text = item?.email
        holder.addresseTextView.text= item.location?.street?.number.toString() + " " + item.location?.street?.name + " " + item.location?.state + " " + item.location?.city
        holder.itemTextView.setOnClickListener {
            onItemClickListener(item)
        }

        if (item.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(item.picture?.large).into(holder.image)
        }
    }
    fun refreshList(contactFromAPI: ArrayList<Results>){
        itemlist = contactFromAPI
        notifyDataSetChanged()
    }
}