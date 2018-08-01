package com.arthurivanets.adapster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        listViewBasedDemoButton.setOnClickListener {
            startActivity(ListViewBasedDemoActivity.init(this@MainActivity))
        }

        recyclerViewBasedDemoButton.setOnClickListener {
            startActivity(RecyclerViewBasedDemoActivity.init(this@MainActivity))
        }
    }


}