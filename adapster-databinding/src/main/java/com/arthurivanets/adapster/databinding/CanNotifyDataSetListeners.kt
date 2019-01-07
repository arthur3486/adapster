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

/**
 * A contract to be implemented by the concrete versions of the Observable Adapters,
 * in order to provide the dataset change notification handling capabilities.
 */
interface CanNotifyDataSetListeners<IT, DS : List<IT>> {

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the new item has been added to the underlying dataset.
     */
    fun notifyDataSetItemAdded(item : IT)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the specified item has been updated within the underlying dataset.
     */
    fun notifyDataSetItemUpdated(item : IT)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the specified old item has been replaced with a new item within the underlying dataset.
     */
    fun notifyDataSetItemReplaced(oldItem : IT, newItem : IT)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the specified item has been removed from the underlying dataset.
     */
    fun notifyDataSetItemRemoved(item : IT)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the size of the underlying dataset has changed.
     */
    fun notifyDataSetSizeChanged(oldSize : Int, newSize : Int)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the underlying dataset has been replaced with a new one.
     */
    fun notifyDataSetReplaced(items : DS)

    /**
     * Notifies all the registered adapter dataset change listeners
     * about the fact that the underlying dataset has been cleared.
     */
    fun notifyDataSetCleared(items : DS)

}