package com.arthurivanets.adapster.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.R
import com.arthurivanets.adapster.listeners.ItemClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.SampleModel
import com.arthurivanets.adapster.model.markers.Trackable

/**
 * Created by arthur3486
 */
class SimpleItem(itemModel : SampleModel) : BaseItem<SampleModel, SimpleItem.ViewHolder, ItemResources>(itemModel), Trackable<Long> {


    override fun init(adapter : Adapter<BaseItem<*, *, *>>?,
                      parent : ViewGroup?,
                      inflater : LayoutInflater?,
                      resources : ItemResources?) : ViewHolder {
        val inflatedView = inflater!!.inflate(MAIN_LAYOUT_ID, parent, false)

        val viewHolder = ViewHolder(inflatedView)
        viewHolder.titleTv = inflatedView.findViewById(R.id.titleTv)
        viewHolder.fullTextTv = inflatedView.findViewById(R.id.fullTextTv)

        return viewHolder
    }


    override fun bind(adapter : Adapter<BaseItem<*, *, *>>?,
                      viewHolder : ViewHolder?,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder?.titleTv?.text = itemModel.title
        viewHolder?.fullTextTv?.text = itemModel.fullText
    }


    fun setOnItemClickListener(viewHolder : ViewHolder?, onItemClickListener : OnItemClickListener<SimpleItem>?) {
        viewHolder?.itemView?.setOnClickListener(ItemClickListener(this, 0, onItemClickListener))
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    override fun getTrackKey() : Long {
        return itemModel.id
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<SampleModel>(itemView) {

        @JvmField var titleTv : TextView? = null
        @JvmField var fullTextTv : TextView? = null

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.sample_item_layout

    }


}