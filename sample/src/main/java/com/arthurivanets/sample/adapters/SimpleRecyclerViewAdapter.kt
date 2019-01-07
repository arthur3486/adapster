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
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.model.FooterItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionsItem

class SimpleRecyclerViewAdapter(
    context : Context,
    items : MutableList<BaseItem<*, *, *>>
) : TrackableRecyclerViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var onArticleItemClickListener : OnItemClickListener<ArticleItem>? = null
    var onTopicSuggestionItemClickListener : OnItemClickListener<TopicSuggestionItem>? = null
    var onTopicSuggestionItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>? = null
    var onFooterButtonClickListener : OnItemClickListener<FooterItem>? = null


    init {
        setHasStableIds(true)
    }


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        when(item) {
            is ArticleItem -> onArticleItemClickListener?.let { item.setOnItemClickListener((holder as ArticleItem.ViewHolder), it) }
            is FooterItem -> onFooterButtonClickListener?.let { item.setOnButtonClickListener((holder as FooterItem.ViewHolder), it) }
            is TopicSuggestionsItem -> {
                onTopicSuggestionItemClickListener?.let { item.setOnItemClickListener((holder as TopicSuggestionsItem.ViewHolder), it) }
                onTopicSuggestionItemLongClickListener?.let { item.setOnItemLongClickListener((holder as TopicSuggestionsItem.ViewHolder), it) }
            }
        }
    }


}