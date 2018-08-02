package com.arthurivanets.sample.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listview.TrackableListViewAdapter
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.model.FooterItem
import com.arthurivanets.sample.adapters.model.TopicItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionsItem

/**
 * Created by arthur3486
 */
class SimpleListViewAdapter(context : Context,
                            items : MutableList<BaseItem<*, *, *>>) : TrackableListViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var mOnItemClickListener : OnItemClickListener<ArticleItem>? = null


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        if(item.getLayout() == ArticleItem.MAIN_LAYOUT_ID) {
            (item as ArticleItem).setOnItemClickListener((holder as ArticleItem.ViewHolder), mOnItemClickListener)
        }
    }


    override fun getItemViewType(position : Int, item : BaseItem<*, *, *>) : Int {
        return when(item.getLayout()) {
            ArticleItem.MAIN_LAYOUT_ID -> 0
            TopicItem.MAIN_LAYOUT_ID -> 1
            TopicSuggestionsItem.MAIN_LAYOUT_ID -> 2
            FooterItem.MAIN_LAYOUT_ID -> 3
            else -> super.getItemViewType(position, item)
        }
    }


    override fun getViewTypeCount() : Int {
        return 4
    }


}