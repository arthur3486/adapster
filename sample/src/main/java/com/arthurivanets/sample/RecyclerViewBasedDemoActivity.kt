package com.arthurivanets.sample

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthurivanets.sample.R
import com.arthurivanets.sample.adapters.*
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.sample.adapters.*
import com.arthurivanets.sample.model.Info
import com.arthurivanets.sample.model.SampleModel
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
        val adapter = SimpleRecyclerViewAdapter(this@RecyclerViewBasedDemoActivity, generateRandomItems(50, 7))
        adapter.mOnItemClickListener = OnItemClickListener { view, item, position ->
            val newItem = SimpleItem(
                    SampleModel(
                            (item.trackKey + 1),
                            "A Title ${(item.trackKey + 1)}",
                            "A Full Text ${(item.trackKey + 1)}"
                    )
            )

            adapter.addOrUpdateItem(adapter.indexOf(item), newItem)
        }
        adapter.addHeader(HeaderItem(Info(Color.parseColor("#025fee"), "Header Item")))
        adapter.addFooter(FooterItem(Info(Color.parseColor("#ad02ee"), "Footer Item")))

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    private fun generateRandomItems(amount : Int, step : Int) : ArrayList<BaseItem<*, *, *>> {
        val items = ArrayList<BaseItem<*, *, *>>()
        var counter = 0

        for(i in 0 until (amount * step) step step) {
            items.add(SimpleItem(
                    SampleModel(
                            i.toLong(),
                            "The Solar System is the gravitationally bound system[$i]...",
                            "The Solar System[a$i] is the gravitationally bound system comprising the Sun and the objects that orbit it, either directly or indirectly.[b] Of the objects that orbit the Sun directly, the largest eight are the planets,[c] with the remainder being smaller objects, such as dwarf planets and small Solar System bodies. Of the objects that orbit the Sun indirectly, the moons, two are larger than the smallest planet, Mercury.[d]"
                    )
            ))

            if(counter >= step) {
                items.add(EmptyItem())
                counter = 0
            }

            counter++
        }

        return items
    }


}