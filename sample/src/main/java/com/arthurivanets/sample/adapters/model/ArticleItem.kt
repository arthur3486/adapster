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
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Trackable
import com.arthurivanets.sample.R
import com.arthurivanets.sample.model.Article
import com.arthurivanets.sample.util.getDimension
import com.arthurivanets.sample.util.getDimensionPixelSize
import com.arthurivanets.sample.widget.TAFrameLayout
import com.squareup.picasso.Picasso

class ArticleItem(itemModel : Article) : BaseItem<Article, ArticleItem.ViewHolder, ItemResources>(itemModel), Trackable<Int> {


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

        viewHolder.titleTv.text = itemModel.title
        viewHolder.contentTv.text = itemModel.text
        viewHolder.imageContainerFl.visibility = (if(itemModel.hasImage) View.VISIBLE else View.GONE)

        if(itemModel.hasImage) {
            with(viewHolder.itemView.context) {
                viewHolder.imageContainerFl.setCornerRadius(getDimension(R.dimen.article_image_corner_radius))

                Picasso.with(applicationContext)
                    .load(itemModel.imageUrl)
                    .resize(
                        getDimensionPixelSize(R.dimen.article_image_size),
                        getDimensionPixelSize(R.dimen.article_image_size)
                    )
                    .centerCrop()
                    .into(viewHolder.imageIv)
            }
        }
    }


    fun setOnItemClickListener(viewHolder : ViewHolder, onItemClickListener : OnItemClickListener<ArticleItem>) {
        viewHolder.itemView.setOnItemClickListener(this, 0, onItemClickListener)
    }


    override fun getLayout() : Int {
        return R.layout.article_item_layout
    }


    override fun getTrackKey() : Int {
        return itemModel.id
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Article>(itemView) {

        val titleTv = itemView.findViewById<TextView>(R.id.titleTv)
        val contentTv = itemView.findViewById<TextView>(R.id.contentTv)
        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val imageContainerFl = itemView.findViewById<TAFrameLayout>(R.id.imageContainerFl)

    }


}