/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
            layout,
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


    fun setOnItemClickListener(viewHolder : ViewHolder, onItemClickListener : OnItemClickListener<TopicSuggestionItem>) {
        viewHolder.adapter.onItemClickListener = onItemClickListener
    }


    fun setOnItemLongClickListener(viewHolder : ViewHolder, onItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>) {
        viewHolder.adapter.onItemLongClickListener = onItemLongClickListener
    }


    override fun getLayout() : Int {
        return R.layout.topic_suggestions_item_layout
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Suggestions<TopicSuggestionItem>>(itemView) {

        val adapter = TopicSuggestionsRecyclerViewAdapter(itemView.context, ArrayList())
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recyclerView)

        init {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }

    }


}