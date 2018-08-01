package com.arthurivanets.adapster.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listview.TrackableListViewAdapter
import com.arthurivanets.adapster.model.BaseItem

/**
 * Created by arthur3486
 */
class SimpleListViewAdapter(context : Context,
                            items : ArrayList<BaseItem<*, *, *>>) : TrackableListViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var mOnItemClickListener : OnItemClickListener<SimpleItem>? = null


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        if(item.getLayout() == SimpleItem.MAIN_LAYOUT_ID) {
            (item as SimpleItem).setOnItemClickListener((holder as SimpleItem.ViewHolder), mOnItemClickListener)
        }
    }


    override fun getItemViewType(position : Int, item : BaseItem<*, *, *>?) : Int {
        return when(item!!.getLayout()) {
            SimpleItem.MAIN_LAYOUT_ID -> 0
            EmptyItem.MAIN_LAYOUT_ID -> 1
            HeaderItem.MAIN_LAYOUT_ID -> 2
            FooterItem.MAIN_LAYOUT_ID -> 3
            else -> super.getItemViewType(position, item)
        }
    }


    override fun getViewTypeCount() : Int {
        return 4
    }


}