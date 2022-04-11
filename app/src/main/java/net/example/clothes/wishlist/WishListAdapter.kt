package net.example.clothes.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.example.clothes.R
import net.example.clothes.model.CatalogueModel

class WishListAdapter(val clickListener: clickedListener)
    : RecyclerView.Adapter<WishListAdapter.WishListAdapterVh>() {

    var wishList = listOf<CatalogueModel.ProductsBean>()

    fun setData(wishList: List<CatalogueModel.ProductsBean>){
        this.wishList = wishList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListAdapterVh {
        return WishListAdapterVh(
            LayoutInflater.from(parent.context).inflate(R.layout.row_wishlist,parent,false)
        )
    }

    override fun onBindViewHolder(holder: WishListAdapterVh, position: Int) {
        var wishResponse = wishList[position]

        holder.tvName.text = wishResponse.name
        holder.tvPrice.text = "$"+wishResponse.price
        Picasso.get().load(wishResponse.image).into(holder.ivCatalogue);

        holder.imageView.setOnClickListener {
            clickListener.addBucketClicked(wishResponse)
        }

    }

    override fun getItemCount(): Int {
        return wishList.size
    }

     interface clickedListener {
        fun Clicked(story: CatalogueModel.ProductsBean)
        fun addBucketClicked(product: CatalogueModel.ProductsBean)
    }

     class WishListAdapterVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var ivCatalogue = itemView.findViewById<ImageView>(R.id.ivCatalogue)
        var tvName = itemView.findViewById<TextView>(R.id.tvName)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
         var viewForeground = itemView.findViewById<LinearLayout>(R.id.view_foreground)
          var viewBackground = itemView.findViewById<RelativeLayout>(R.id.view_background)

    }


}