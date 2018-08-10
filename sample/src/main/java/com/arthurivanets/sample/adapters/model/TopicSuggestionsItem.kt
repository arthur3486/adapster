package com.arthurivanets.sample.adapters.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.sample.R
import com.arthurivanets.sample.adapters.TopicSuggestionsRecyclerViewAdapter
import com.arthurivanets.sample.model.Suggestions

class TopicSuggestionsItem(itemModel : Suggestions<TopicSuggestionItem>) : BaseItem<Suggestions<TopicSuggestionItem>, TopicSuggestionsItem.ViewHolder, ItemResources>(itemModel) {


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

        viewHolder.adapter.items = itemModel.suggestedItems
    }


    fun setOnItemClickListener(viewHolder : ViewHolder, onItemClickListener : OnItemClickListener<TopicSuggestionItem>?) {
        viewHolder.adapter.onItemClickListener = onItemClickListener
    }


    fun setOnItemLongClickListener(viewHolder : ViewHolder, onItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>?) {
        viewHolder.adapter.onItemLongClickListener = onItemLongClickListener
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Suggestions<TopicSuggestionItem>>(itemView) {

        val adapter = TopicSuggestionsRecyclerViewAdapter(itemView.context, ArrayList())
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)

        init {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.topic_suggestions_item_layout

    }


}