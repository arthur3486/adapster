package com.arthurivanets.sample.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter
import com.arthurivanets.sample.adapters.model.TopicSuggestionItem

class TopicSuggestionsRecyclerViewAdapter(context : Context,
                                          items : MutableList<TopicSuggestionItem>) : TrackableRecyclerViewAdapter<Long, TopicSuggestionItem, TopicSuggestionItem.ViewHolder>(context, items) {


    var onItemClickListener : OnItemClickListener<TopicSuggestionItem>? = null
    var onItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>? = null


    init {
        setHasStableIds(true)
    }


    override fun assignListeners(holder : TopicSuggestionItem.ViewHolder, position : Int, item : TopicSuggestionItem) {
        super.assignListeners(holder, position, item)

        item.setOnItemClickListener(holder, onItemClickListener)
        item.setOnItemLongClickListener(holder, onItemLongClickListener)
    }


}