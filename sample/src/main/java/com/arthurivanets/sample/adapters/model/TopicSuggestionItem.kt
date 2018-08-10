package com.arthurivanets.sample.adapters.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.listeners.ItemClickListener
import com.arthurivanets.adapster.listeners.ItemLongClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.sample.R
import com.arthurivanets.sample.model.Topic
import com.arthurivanets.sample.util.getDimensionPixelSize
import com.squareup.picasso.Picasso

class TopicSuggestionItem(itemModel : Topic) : BaseItem<Topic, TopicSuggestionItem.ViewHolder, ItemResources>(itemModel) {


    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>?,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        return ViewHolder(inflater.inflate(
            MAIN_LAYOUT_ID,
            parent,
            false
        ))
    }


    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>?,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder.nameTv.text = itemModel.name

        with(viewHolder.imageIv.context) {
            Picasso.with(applicationContext)
                .load(itemModel.imageUrl)
                .resize(
                    getDimensionPixelSize(R.dimen.topic_suggestion_item_width),
                    getDimensionPixelSize(R.dimen.topic_suggestion_item_height)
                )
                .centerCrop()
                .into(viewHolder.imageIv)
        }
    }


    fun setOnItemClickListener(viewHolder : ViewHolder, onItemClickListener : OnItemClickListener<TopicSuggestionItem>?) {
        viewHolder.itemView.setOnClickListener(ItemClickListener(this, 0, onItemClickListener))
    }


    fun setOnItemLongClickListener(viewHolder : ViewHolder, onItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>?) {
        viewHolder.itemView.setOnLongClickListener(ItemLongClickListener(this, 0, onItemLongClickListener))
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Topic>(itemView) {

        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val nameTv = itemView.findViewById<TextView>(R.id.nameTv)

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.topic_suggestion_item_layout

    }


}