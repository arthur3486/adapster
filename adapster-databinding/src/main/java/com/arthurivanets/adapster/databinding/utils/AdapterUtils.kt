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
 * The specified action will be executed, if the current object is [Trackable].
 *
 * @param action the action to be executed when the conditions are satisfied
 * @return the corresponding [Trackable] if the current object is indeed trackable, or <strong>null</strong otherwise
 */
inline fun <T> Any.ifTrackable(action : (Trackable<T>) -> Unit = {}) : Trackable<T>? {
    return if(isTrackable()) {
        action(this as Trackable<T>)
        this
    } else {
        null
    }
}


/**
 * Determines whether the current object is [Trackable].
 *
 * @return <strong>true</strong> if the current object is indeed [Trackable], <strong>false</strong> otherwise
 */
fun Any.isTrackable() : Boolean {
    return (this is Trackable<*>)
}


/**
 * Casts the current object to a [Trackable] (if possible).
 *
 * @throws ClassCastException if the current object isn't an instance of [Trackable]
 */
fun <T> Any.asTrackable() : Trackable<T> {
    return (this as Trackable<T>)
}


/**
 * Executes the specified action, if the current instance of the [TrackableRecyclerViewAdapter] can be notified about the dataset changes
 * (is an instance of the [CanNotifyDataSetListeners]).
 *
 * @param action the action to be executed when the conditions are satisfied
 */
inline fun <KT, IT : BaseItem<*, *, *>> TrackableRecyclerViewAdapter<KT, IT, *>.ifCanNotifyDataSetListeners(action : CanNotifyDataSetListeners<IT, MutableList<IT>>.() -> Unit) {
    if(this is CanNotifyDataSetListeners<*, *>) {
        action(this as CanNotifyDataSetListeners<IT, MutableList<IT>>)
    }
}


/**
 * Iterates over the specified range of the items and executes the specified action for each of them.
 *
 * @param startIndex the start index of the range
 * @param endIndex the end index of the range
 * @param action the action to be executed for each corresponding item found within the specified range
 */
inline fun <T> List<T>.forEachInRange(startIndex : Int,
                                      endIndex : Int,
                                      action : (T) -> Unit) {
    for(index in Math.max(startIndex, 0)..Math.min(endIndex, (size - 1))) {
        action(this[index])
    }
}