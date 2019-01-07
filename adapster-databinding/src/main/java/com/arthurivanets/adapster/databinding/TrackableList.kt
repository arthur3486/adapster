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

import com.arthurivanets.adapster.model.markers.Trackable

/**
 * A base contract to be implemented by the concrete collections in order to provide
 * the item tracking capabilities.
 */
interface TrackableList<KT : Any, IT : Any> : MutableList<IT> {

    /**
     * Replaces the items within the current dataset with the specified ones, using the predicate
     * to allow you to make the final decision on whether to apply the calculated changes or not.
     *
     * @param items items to be added to the dataset
     * @param predicate predicate used to determine whether to apply the calculated dataset change associated with the specified item.
     */
    fun setItems(items : List<IT>, predicate : (IT) -> Boolean = { true })

    /**
     * Determines whether the current dataset contains an item that corresponds to the specified key.
     *
     * @return <strong>true</strong> if the corresponding item is present, <strong>false</strong> otherwise
     */
    fun containsByKey(key : KT) : Boolean

    /**
     * Attempts to retrieve a [Trackable] from the current dataset that corresponds to the specified key.
     *
     * @return the corresponding [Trackable] if there's any, or <strong>null</strong> otherwise
     */
    fun getTrackable(key : KT) : Trackable<KT>?

    /**
     * Attempts to retrieve a [Trackable] from the current dataset that corresponds to the specified key,
     * and cast it to the specified type thereafter.
     *
     * @return the corresponding [Trackable] cast to type [T], or <strong>null</strong> otherwise
     */
    fun <T> getTrackableAs(key : KT) : T?

}