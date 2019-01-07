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
import com.arthurivanets.adapster.listview.TrackableListViewAdapter
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.model.FooterItem
import com.arthurivanets.sample.adapters.model.TopicItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionsItem

class SimpleListViewAdapter(
    context : Context,
    items : MutableList<BaseItem<*, *, *>>
) : TrackableListViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var mOnItemClickListener : OnItemClickListener<ArticleItem>? = null


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        when(item) {
            is ArticleItem -> mOnItemClickListener?.let { item.setOnItemClickListener((holder as ArticleItem.ViewHolder), it) }
        }
    }


    override fun getItemViewType(position : Int, item : BaseItem<*, *, *>) : Int {
        return when(item) {
            is ArticleItem -> 0
            is TopicItem -> 1
            is TopicSuggestionsItem -> 2
            is FooterItem -> 3
            else -> super.getItemViewType(position, item)
        }
    }


    override fun getViewTypeCount() : Int {
        return 4
    }


}