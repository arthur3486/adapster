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
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.sample.adapters.SimpleListViewAdapter
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.model.FooterItem
import com.arthurivanets.sample.adapters.model.TopicItem
import com.arthurivanets.sample.util.DataProvider
import kotlinx.android.synthetic.main.list_view_based_demo_activity_layout.*

class ListViewBasedDemoActivity : AppCompatActivity() {


    companion object {

        @JvmStatic fun init(context : Context) : Intent {
            return Intent(context, ListViewBasedDemoActivity::class.java)
        }

    }


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_based_demo_activity_layout)
        init()
    }


    private fun init() {
        val datasetItems : MutableList<BaseItem<*, *, *>> = DataProvider.ATRICLES
            .map { ArticleItem(it) }
            .toMutableList()

        val adapter = SimpleListViewAdapter(this@ListViewBasedDemoActivity, datasetItems)
        adapter.addHeader(TopicItem(DataProvider.PLANETS_TOPICS[0]))
        adapter.addFooter(FooterItem(DataProvider.FOOTER_INFO))

        listView.adapter = adapter
    }


}