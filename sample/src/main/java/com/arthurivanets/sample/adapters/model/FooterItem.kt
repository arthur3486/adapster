package com.arthurivanets.sample.adapters.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.listeners.ItemClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Footer
import com.arthurivanets.sample.R
import com.arthurivanets.sample.model.FooterInfo
import com.squareup.picasso.Picasso

class FooterItem(itemModel : FooterInfo) : BaseItem<FooterInfo, FooterItem.ViewHolder, ItemResources>(itemModel),
        Footer<BaseItem.ViewHolder<*>> {


    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        return ViewHolder(inflater.inflate(
            MAIN_LAYOUT_ID,
            parent,
            false
        ))
    }


    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder.messageTv.text = itemModel.message
        viewHolder.buttonTv.text = itemModel.buttonTitle

        Picasso.with(viewHolder.imageIv.context.applicationContext)
            .load(itemModel.imageUrl)
            //.centerCrop()
            .into(viewHolder.imageIv)
    }


    override fun setOnItemClickListener(viewHolder : BaseItem.ViewHolder<*>, onItemClickListener : OnItemClickListener<Footer<BaseItem.ViewHolder<*>>>?) {
        viewHolder.itemView.setOnClickListener(ItemClickListener(this, 0, onItemClickListener))
    }


    fun setOnButtonClickListener(viewHolder : ViewHolder, onButtonClickListener : OnItemClickListener<FooterItem>?) {
        viewHolder.buttonTv.setOnClickListener(ItemClickListener(this, 0, onButtonClickListener))
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<FooterInfo>(itemView) {

        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val messageTv = itemView.findViewById<TextView>(R.id.messageTv)
        val buttonTv = itemView.findViewById<TextView>(R.id.buttonTv)

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.footer_item_layout

    }


}