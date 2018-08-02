package com.arthurivanets.sample.adapters

import android.content.Context
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemLongClickListener
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.recyclerview.TrackableRecyclerViewAdapter
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.model.FooterItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionItem
import com.arthurivanets.sample.adapters.model.TopicSuggestionsItem

class SimpleRecyclerViewAdapter(context : Context,
                                items : MutableList<BaseItem<*, *, *>>) : TrackableRecyclerViewAdapter<Long, BaseItem<*, *, *>, BaseItem.ViewHolder<*>>(context, items) {


    var onArticleItemClickListener : OnItemClickListener<ArticleItem>? = null
    var onTopicSuggestionItemClickListener : OnItemClickListener<TopicSuggestionItem>? = null
    var onTopicSuggestionItemLongClickListener : OnItemLongClickListener<TopicSuggestionItem>? = null
    var onFooterButtonClickListener : OnItemClickListener<FooterItem>? = null


    init {
        setHasStableIds(true)
    }


    override fun assignListeners(holder : BaseItem.ViewHolder<*>, position : Int, item : BaseItem<*, *, *>) {
        super.assignListeners(holder, position, item)

        when(item.getLayout()) {
            ArticleItem.MAIN_LAYOUT_ID -> (item as ArticleItem).setOnItemClickListener((holder as ArticleItem.ViewHolder), onArticleItemClickListener)
            FooterItem.MAIN_LAYOUT_ID -> (item as FooterItem).setOnButtonClickListener((holder as FooterItem.ViewHolder), onFooterButtonClickListener)
            TopicSuggestionsItem.MAIN_LAYOUT_ID -> {
                (item as TopicSuggestionsItem).setOnItemClickListener((holder as TopicSuggestionsItem.ViewHolder), onTopicSuggestionItemClickListener)
                item.setOnItemLongClickListener(holder, onTopicSuggestionItemLongClickListener)
            }
        }
    }


}