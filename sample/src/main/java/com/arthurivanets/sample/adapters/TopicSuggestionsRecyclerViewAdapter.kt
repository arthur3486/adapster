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

package com.arthurivanets.sample.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter
import com.arthurivanets.sample.adapters.model.TopicSuggestionItem

class TopicSuggestionsRecyclerViewAdapter(
    context : Context,
    items : MutableList<TopicSuggestionItem>
) : TrackableRecyclerViewAdapter<Long, TopicSuggestionItem, TopicSuggestionItem.ViewHolder>(context, items) {


    var onItemClickListener : OnItemClickListener<TopicSuggestionItem>? = null
    var onItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>? = null


    init {
        setHasStableIds(true)
    }


    override fun assignListeners(holder : TopicSuggestionItem.ViewHolder,
                                 position : Int,
                                 item : TopicSuggestionItem) {
        super.assignListeners(holder, position, item)

        onItemClickListener?.let { item.setOnItemClickListener(holder, it) }
        onItemLongClickListener?.let { item.setOnItemLongClickListener(holder, it) }
    }


}