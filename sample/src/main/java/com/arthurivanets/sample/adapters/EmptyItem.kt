package com.arthurivanets.sample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.sample.R
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item

/**
 * Created by arthur3486
 */
class EmptyItem : BaseItem<Unit, EmptyItem.ViewHolder, ItemResources>(null) {


    override fun init(adapter : Adapter<out Item<*, *>>,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        val inflatedView = inflater.inflate(MAIN_LAYOUT_ID, parent, false)

        val viewHolder = ViewHolder(inflatedView)

        return viewHolder
    }


    override fun bind(adapter : Adapter<out Item<*, *>>,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Unit>(itemView) {

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.even_simpler_item_layout

    }


}