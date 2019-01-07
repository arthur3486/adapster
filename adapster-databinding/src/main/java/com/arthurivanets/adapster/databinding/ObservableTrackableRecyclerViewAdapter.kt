/*
 * Copyright 2018 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

package com.arthurivanets.adapster.databinding

import android.content.Context
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter

/**
 * A base Adapter implementation to be used for your concrete Adapters when using Data Binding.
 */
abstract class ObservableTrackableRecyclerViewAdapter<KT : Any, IT : BaseItem<*, *, *>, VH : BaseItem.ViewHolder<*>>(
    context : Context,
    items : TrackableList<KT, IT>
) : TrackableRecyclerViewAdapter<KT, IT, VH>(context, items), CanNotifyDataSetListeners<IT, MutableList<IT>> {


    private val internalListChangeListener = ObservableTrackableRecyclerViewAdapterDataSetChangeHandler<KT, IT>()


    @Deprecated("You shouldn't replace the Dataset of an Observable Adapter.")
    override fun setItems(items : MutableList<IT>) {
        super.setItems(items)
    }


    override fun onAttachedToRecyclerView(recyclerView : RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        bindListChangeListener()
    }


    override fun onDetachedFromRecyclerView(recyclerView : RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        unbindListChangeListener()
    }


    private fun bindListChangeListener() {
        internalListChangeListener.attachAdapter(this)

        if(items is ObservableList) {
            (items as ObservableList<IT>).addOnListChangedCallback(internalListChangeListener)
        }
    }


    private fun unbindListChangeListener() {
        if(items is ObservableList) {
            (items as ObservableList<IT>).removeOnListChangedCallback(internalListChangeListener)
        }

        internalListChangeListener.detachAdapter()
    }


    override fun notifyDataSetItemAdded(item : IT) {
        trackIfNecessary(item)
        notifyItemAdded(item)
    }


    override fun notifyDataSetItemUpdated(item : IT) {
        trackIfNecessary(item)
        notifyItemUpdated(item)
    }


    override fun notifyDataSetItemReplaced(oldItem : IT, newItem : IT) {
        untrackIfNecessary(oldItem)
        trackIfNecessary(newItem)
        notifyItemReplaced(oldItem, newItem)
    }


    override fun notifyDataSetItemRemoved(item : IT) {
        untrackIfNecessary(item)
        notifyItemDeleted(item)
    }


    override fun notifyDataSetSizeChanged(oldSize : Int, newSize : Int) {
        notifyDatasetSizeChanged(oldSize, newSize)
    }


    override fun notifyDataSetReplaced(items : MutableList<IT>) {
        throw UnsupportedOperationException("Dataset replacement is not supported in Observable Adapters.")
    }


    override fun notifyDataSetCleared(items : MutableList<IT>) {
        clear()
    }


}