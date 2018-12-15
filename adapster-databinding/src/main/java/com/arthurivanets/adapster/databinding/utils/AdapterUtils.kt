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

@file:JvmName("AdapterUtils")

package com.arthurivanets.adapster.databinding.utils

import com.arthurivanets.adapster.databinding.CanNotifyDataSetListeners
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.markers.Trackable
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter


/**
 *
 */
inline fun <T> Any.ifTrackable(block : (Trackable<T>) -> Unit = {}) : Trackable<T>? {
    return if(isTrackable()) {
        block(this as Trackable<T>)
        this
    } else {
        null
    }
}


/**
 *
 */
fun Any.isTrackable() : Boolean {
    return (this is Trackable<*>)
}


/**
 *
 */
fun <T> Any.asTrackable() : Trackable<T> {
    return (this as Trackable<T>)
}


/**
 *
 */
inline fun <KT, IT : BaseItem<*, *, *>> TrackableRecyclerViewAdapter<KT, IT, *>.ifCanNotifyDataSetListeners(block : CanNotifyDataSetListeners<IT, MutableList<IT>>.() -> Unit) {
    if(this is CanNotifyDataSetListeners<*, *>) {
        block(this as CanNotifyDataSetListeners<IT, MutableList<IT>>)
    }
}


/**
 *
 */
inline fun <T> List<T>.forEachInRange(startIndex : Int, endIndex : Int, block : (T) -> Unit) {
    for(index in Math.max(startIndex, 0)..Math.min(endIndex, (size - 1))) {
        block(this[index])
    }
}
