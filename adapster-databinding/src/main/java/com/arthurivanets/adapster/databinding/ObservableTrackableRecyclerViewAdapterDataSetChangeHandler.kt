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

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.arthurivanets.adapster.databinding.utils.forEachInRange
import com.arthurivanets.adapster.databinding.utils.ifCanNotifyDataSetListeners
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter

/**
 * An implementation of the Adapter Dataset Change Handler designed specifically for the Observable Adapters & Datasets.
 */
open class ObservableTrackableRecyclerViewAdapterDataSetChangeHandler<KT, IT : BaseItem<*, *, *>> : ObservableList.OnListChangedCallback<ObservableList<IT>>() {


    private var adapter : TrackableRecyclerViewAdapter<KT, IT, *>? = null


    fun attachAdapter(adapter : TrackableRecyclerViewAdapter<KT, IT, *>) {
        this.adapter = adapter
    }


    fun detachAdapter() {
        this.adapter = null
    }


    override fun onChanged(sender : ObservableList<IT>?) {
        onChangedInternal(sender ?: ObservableArrayList())
    }


    private fun onChangedInternal(sender : MutableList<IT>) {
        adapter?.notifyDataSetChanged()
        adapter?.ifCanNotifyDataSetListeners { sender.let(::onDataSetReplaced) }
    }


    override fun onItemRangeInserted(sender : ObservableList<IT>?,
                                     positionStart : Int,
                                     itemCount : Int) {
        onItemRangeInsertedInternal(
            sender = (sender ?: emptyList()),
            positionStart = positionStart,
            itemCount = itemCount
        )
    }


    private fun onItemRangeInsertedInternal(sender : List<IT>,
                                            positionStart : Int,
                                            itemCount : Int) {
        adapter?.notifyItemRangeInserted(positionStart, itemCount)
        adapter?.ifCanNotifyDataSetListeners {
            sender.forEachInRange(
                startIndex = positionStart,
                endIndex = (positionStart + itemCount - 1),
                action = ::onDataSetItemAdded
            )
        }
        reportDataSetSizeChanged(
            oldSize = (sender.size - itemCount),
            newSize = sender.size
        )
    }


    override fun onItemRangeRemoved(sender : ObservableList<IT>?,
                                    positionStart : Int,
                                    itemCount : Int) {
        onItemRangeRemovedInternal(
            sender = (sender ?: emptyList()),
            positionStart = positionStart,
            itemCount = itemCount
        )
    }


    private fun onItemRangeRemovedInternal(sender : List<IT>,
                                           positionStart : Int,
                                           itemCount : Int) {
        adapter?.notifyItemRangeRemoved(positionStart, itemCount)
        reportDataSetSizeChanged(
            oldSize = (sender.size + itemCount),
            newSize = sender.size
        )
    }


    override fun onItemRangeMoved(sender : ObservableList<IT>?,
                                  fromPosition : Int,
                                  toPosition : Int,
                                  itemCount : Int) {
        onItemRangeRemovedInternal(
            sender = (sender ?: emptyList()),
            positionStart = fromPosition,
            itemCount = itemCount
        )
        onItemRangeInsertedInternal(
            sender = (sender ?: emptyList()),
            positionStart = toPosition,
            itemCount = itemCount
        )
    }


    override fun onItemRangeChanged(sender : ObservableList<IT>?,
                                    positionStart : Int,
                                    itemCount : Int) {
        onItemRangeChangedInternal(
            sender = sender,
            positionStart = positionStart,
            itemCount = itemCount
        )
    }


    private fun onItemRangeChangedInternal(sender : ObservableList<IT>?,
                                           positionStart : Int,
                                           itemCount : Int) {
        adapter?.notifyItemRangeChanged(positionStart, itemCount)
        adapter?.ifCanNotifyDataSetListeners {
            sender?.forEachInRange(
                startIndex = positionStart,
                endIndex = (positionStart + itemCount - 1),
                action = ::onDataSetItemUpdated
            )
        }
    }


    private fun reportDataSetSizeChanged(oldSize : Int, newSize : Int) {
        adapter?.ifCanNotifyDataSetListeners {
            onDataSetSizeChanged(
                oldSize = oldSize,
                newSize = newSize
            )
        }
    }


}