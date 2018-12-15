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

@file:JvmName("ListenerExtensions")

package com.arthurivanets.adapster.ktx

import android.view.View
import com.arthurivanets.adapster.listeners.*
import com.arthurivanets.adapster.model.Item


/**
 * Creates an [ItemClickListener] out of the specified parameters and assigns it to the [View].
 *
 * @param item the [com.arthurivanets.adapster.model.BaseItem]'s item model
 * @param position the position of the item within the dataset (optional)
 * @param onItemClickListener the actual item click event handler (listener)
 */
fun <VT : View, IT : Item<*, *>> VT.setOnItemClickListener(item : IT,
                                                           position : Int,
                                                           onItemClickListener : OnItemClickListener<IT>?) {
    this.setOnClickListener(ItemClickListener(
        item,
        position,
        onItemClickListener
    ))
}


/**
 * Creates an [ItemLongClickListener] out of the specified parameters and assigns it to the [View].
 *
 * @param item the [com.arthurivanets.adapster.model.BaseItem]'s item model
 * @param position the position of the item within the dataset (optional)
 * @param onItemLongClickListener the actual item long click event handler (listener)
 */
fun <VT : View, IT : Item<*, *>> VT.setOnItemLongClickListener(item : IT,
                                                               position : Int,
                                                               onItemLongClickListener : OnItemLongClickListener<IT>?) {
    this.setOnLongClickListener(ItemLongClickListener(
        item,
        position,
        onItemLongClickListener
    ))
}


/**
 * Creates an [ItemTouchListener] out of the specified parameters and assigns it to the [View].
 *
 * @param item the [com.arthurivanets.adapster.model.BaseItem]'s item model
 * @param position the position of the item within the dataset (optional)
 * @param onItemTouchListener the actual item touch event handler (listener)
 */
fun <VT : View, IT : Item<*, *>> VT.setOnItemTouchListener(item : IT,
                                                           position : Int,
                                                           onItemTouchListener : OnItemTouchListener<IT>?) {
    this.setOnTouchListener(ItemTouchListener(
        item,
        position,
        onItemTouchListener
    ))
}