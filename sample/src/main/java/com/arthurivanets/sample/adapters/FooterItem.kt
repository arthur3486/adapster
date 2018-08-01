package com.arthurivanets.sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.sample.R
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Footer
import com.arthurivanets.sample.model.Info

class FooterItem(itemModel : Info) : BaseItem<Info, FooterItem.ViewHolder, ItemResources>(itemModel),
        Footer<BaseItem.ViewHolder<*>> {


    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        val inflatedView = inflater.inflate(MAIN_LAYOUT_ID, parent, false)

        val viewHolder = ViewHolder(inflatedView)

        return viewHolder
    }


    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder.itemView.setBackgroundColor(itemModel.color)
        viewHolder.titleTv?.text = itemModel.title
    }


    override fun setOnItemClickListener(viewHolder : BaseItem.ViewHolder<*>?, onItemClickListener : OnItemClickListener<Footer<BaseItem.ViewHolder<*>>>?) {
        //
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Info>(itemView) {

        val titleTv = itemView.findViewById<TextView>(R.id.titleTv)

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.footer_item_layout

    }


}