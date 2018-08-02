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