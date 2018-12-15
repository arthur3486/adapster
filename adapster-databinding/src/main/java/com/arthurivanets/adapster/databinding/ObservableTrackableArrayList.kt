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
import com.arthurivanets.adapster.databinding.utils.asTrackable
import com.arthurivanets.adapster.databinding.utils.ifTrackable
import com.arthurivanets.adapster.databinding.utils.isTrackable
import com.arthurivanets.adapster.model.markers.Trackable
import java.util.*

/**
 *
 */
open class ObservableTrackableArrayList<KT : Any, IT : Any> : ObservableArrayList<IT>(), TrackableList<KT, IT> {


    private val trackingMap = HashMap<KT, Trackable<KT>>()


    override fun add(element : IT) : Boolean {
        addTrackableIfNecessary(element)

        return super.add(element)
    }


    override fun add(index : Int, element : IT) {
        addTrackableIfNecessary(element)

        super.add(index, element)
    }


    override fun addAll(elements : Collection<IT>) : Boolean {
        addTrackablesIfNecessary(elements)

        return super.addAll(elements)
    }


    override fun addAll(index : Int, elements : Collection<IT>) : Boolean {
        addTrackablesIfNecessary(elements)

        return super.addAll(index, elements)
    }


    open fun addOrUpdate(element : IT) {
        addOrUpdate(size, element)
    }


    open fun addOrUpdate(index : Int, element : IT) {
        if(contains(element)) {
            set(indexOf(element), element)
        } else {
            add(index, element)
        }
    }


    override fun remove(element : IT) : Boolean {
        val index = indexOf(element)

        if(index != -1) {
            super.removeAt(index)
            return true
        } else {
            return false
        }
    }


    override fun removeAt(index : Int) : IT {
        removeTrackableIfNecessary(this[index])

        return super.removeAt(index)
    }


    override fun removeRange(fromIndex : Int, toIndex : Int) {
        for(i in fromIndex..toIndex) {
            removeTrackableIfNecessary(this[i])
        }

        super.removeRange(fromIndex, toIndex)
    }


    override fun set(index : Int, element : IT) : IT {
        this[index].let(::removeTrackableIfNecessary)
        element.also(::addTrackableIfNecessary)

        return super.set(index, element)
    }


    override fun setItems(items : List<IT>, predicate : (IT) -> Boolean) {
        val addedItems = ArrayList<IT>()
        val deletedItems = ArrayList<IT>()
        val newDataSetItemTracker = HashMap<KT, IT>()

        // collecting the items to be added or updated
        for(item in items) {
            item.ifTrackable<KT> {
                newDataSetItemTracker[it.trackKey] = item
            }
            addedItems.add(item)
        }

        // extracting the items to be removed
        for(item in this) {
            item.ifTrackable<KT> {
                if(newDataSetItemTracker[it.trackKey] == null) {
                    deletedItems.add(item)
                }
            }
        }

        // performing the pending actions
        deletedItems.forEach {
            if(predicate(it)) {
                remove(it)
            }
        }
        addedItems.forEach {
            if(predicate(it)) {
                addOrUpdate(it)
            }
        }
    }


    override fun clear() {
        removeAllTrackables()
        super.clear()
    }


    override fun contains(element : IT) : Boolean {
        if(element is Trackable<*>) {
            return containsTrackable((element as Trackable<KT>).trackKey)
        } else {
            return super.contains(element)
        }
    }


    override fun containsByKey(key : KT) : Boolean {
        return containsTrackable(key)
    }


    override fun indexOf(element : IT) : Int {
        return if(element.isTrackable() && contains(element)) {
            super.indexOf(getTrackable(element.asTrackable<KT>().trackKey) as IT)
        } else {
            super.indexOf(element)
        }
    }


    private fun addTrackableIfNecessary(item : IT) {
        item.ifTrackable<KT> { trackingMap[it.trackKey] = it }
    }


    private fun addTrackablesIfNecessary(items : Collection<IT>) {
        items.forEach(::addTrackableIfNecessary)
    }


    private fun removeTrackable(key : KT) : Trackable<KT>? {
        return trackingMap.remove(key)
    }


    private fun removeTrackableIfNecessary(item : IT) : Trackable<KT>? {
        return item?.ifTrackable<KT> { removeTrackable(it.trackKey) }
    }


    private fun removeAllTrackables() {
        trackingMap.clear()
    }


    override fun getTrackable(key : KT) : Trackable<KT>? {
        return trackingMap[key]
    }


    override fun <T> getTrackableAs(key : KT) : T? {
        return (getTrackable(key) as T?)
    }


    private fun containsTrackable(key : KT) : Boolean {
        return (getTrackable(key) != null)
    }


}