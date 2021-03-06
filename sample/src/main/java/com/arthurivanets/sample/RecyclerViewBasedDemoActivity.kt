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

package com.arthurivanets.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.sample.adapters.SimpleRecyclerViewAdapter
import com.arthurivanets.sample.adapters.model.*
import com.arthurivanets.sample.model.Suggestions
import com.arthurivanets.sample.util.DataProvider
import com.arthurivanets.sample.util.toast
import kotlinx.android.synthetic.main.recycler_view_based_demo_activity_layout.*

class RecyclerViewBasedDemoActivity : AppCompatActivity() {


    companion object {

        @JvmStatic fun init(context : Context) : Intent {
            return Intent(context, RecyclerViewBasedDemoActivity::class.java)
        }

    }


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_based_demo_activity_layout)
        init()
    }


    private fun init() {
        // dummy data
        val datasetItems : MutableList<BaseItem<*, *, *>> = DataProvider.ATRICLES
            .map { ArticleItem(it) }
            .toMutableList()

        val planetSuggestionItems = DataProvider.PLANETS_TOPICS.subList(1, DataProvider.PLANETS_TOPICS.size)
            .map { TopicSuggestionItem(it) }
            .toMutableList()

        val generalSuggestionItems = DataProvider.GENERAL_TOPICS
            .map { TopicSuggestionItem(it) }
            .toMutableList()

        // adapter initialization
        val adapter = SimpleRecyclerViewAdapter(this@RecyclerViewBasedDemoActivity, datasetItems)
        adapter.setOnHeaderClickListener { _, item, _ ->
            toast("Header Item Clicked")
        }
        adapter.setOnFooterClickListener { _, item, _ ->
            toast("Footer Item Clicked")
        }
        adapter.onArticleItemClickListener = OnItemClickListener { _, item, _ ->
            toast("Article Item Clicked: ${item.itemModel.title}")
        }
        adapter.onTopicSuggestionItemClickListener = OnItemClickListener { _, item, _ ->
            toast("Topic Suggestion Item Clicked: ${item.itemModel.name}")
        }
        adapter.onTopicSuggestionItemLongClickListener = OnItemLongClickListener { _, item, _ ->
            toast("Topic Suggestion Item Long Clicked: ${item.itemModel.name}")
            true
        }
        adapter.onFooterButtonClickListener = OnItemClickListener { _, item, _ ->
            toast("Footer Item Button Clicked: ${item.itemModel.buttonTitle}")
        }
        adapter.addHeader(TopicItem(DataProvider.PLANETS_TOPICS[0]))
        adapter.addFooter(FooterItem(DataProvider.FOOTER_INFO))

        // carousels
        adapter.addOrUpdateItem(
            5,
            TopicSuggestionsItem(Suggestions(
                id = 3252532,
                suggestedItems = planetSuggestionItems
            ))
        )
        adapter.addOrUpdateItem(
            10,
            TopicSuggestionsItem(Suggestions(
                id = 3252533,
                suggestedItems = generalSuggestionItems
            ))
        )

        // the rest of the recycler view initialization
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


}