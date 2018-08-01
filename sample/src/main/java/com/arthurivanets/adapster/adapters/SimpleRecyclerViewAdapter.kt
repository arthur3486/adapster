package com.arthurivanets.adapster.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter

class SimpleRecyclerViewAdapter(context : Context,
                                items : ArrayList<BaseItem<*, *, *>>) : TrackableRecyclerViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var mOnItemClickListener : OnItemClickListener<SimpleItem>? = null


    init {
        setHasStableIds(true)
    }


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        if(item.getLayout() == SimpleItem.MAIN_LAYOUT_ID) {
            (item as SimpleItem).setOnItemClickListener((holder as SimpleItem.ViewHolder), mOnItemClickListener)
        }
    }


}