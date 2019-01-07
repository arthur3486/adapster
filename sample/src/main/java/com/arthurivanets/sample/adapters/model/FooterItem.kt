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
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.ktx.setOnItemClickListener
import com.arthurivanets.adapster.listeners.ItemClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Footer
import com.arthurivanets.sample.R
import com.arthurivanets.sample.model.FooterInfo
import com.squareup.picasso.Picasso

class FooterItem(itemModel : FooterInfo) : BaseItem<FooterInfo, FooterItem.ViewHolder, ItemResources>(itemModel),
        Footer<FooterItem.ViewHolder> {


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

        viewHolder.messageTv.text = itemModel.message
        viewHolder.buttonTv.text = itemModel.buttonTitle

        Picasso.with(viewHolder.imageIv.context.applicationContext)
            .load(itemModel.imageUrl)
            //.centerCrop()
            .into(viewHolder.imageIv)
    }


    override fun setOnItemClickListener(viewHolder : ViewHolder, onItemClickListener : OnItemClickListener<Footer<ViewHolder>>?) {
        viewHolder.itemView.setOnClickListener(ItemClickListener(this, 0, onItemClickListener))
    }


    fun setOnButtonClickListener(viewHolder : ViewHolder, onButtonClickListener : OnItemClickListener<FooterItem>) {
        viewHolder.buttonTv.setOnItemClickListener(this, 0, onButtonClickListener)
    }


    override fun getLayout() : Int {
        return R.layout.footer_item_layout
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<FooterInfo>(itemView) {

        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val messageTv = itemView.findViewById<TextView>(R.id.messageTv)
        val buttonTv = itemView.findViewById<TextView>(R.id.buttonTv)

    }


}