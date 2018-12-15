package com.arthurivanets.sample.adapters.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.adapster.Adapter
import com.arthurivanets.adapster.ktx.setOnItemClickListener
import com.arthurivanets.adapster.listeners.OnItemClickListener
import com.arthurivanets.adapster.markers.ItemResources
import com.arthurivanets.adapster.model.BaseItem
import com.arthurivanets.adapster.model.Item
import com.arthurivanets.adapster.model.markers.Trackable
import com.arthurivanets.sample.R
import com.arthurivanets.sample.model.Article
import com.arthurivanets.sample.util.getDimension
import com.arthurivanets.sample.util.getDimensionPixelSize
import com.arthurivanets.sample.widget.TAFrameLayout
import com.squareup.picasso.Picasso

/**
 * Created by arthur3486
 */
class ArticleItem(itemModel : Article) : BaseItem<Article, ArticleItem.ViewHolder, ItemResources>(itemModel), Trackable<Int> {


    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>?,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        return ViewHolder(inflater.inflate(
            MAIN_LAYOUT_ID,
            parent,
            false
        ))
    }


    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>?,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder.titleTv.text = itemModel.title
        viewHolder.contentTv.text = itemModel.text
        viewHolder.imageContainerFl.visibility = (if(itemModel.hasImage) View.VISIBLE else View.GONE)

        if(itemModel.hasImage) {
            with(viewHolder.itemView.context) {
                viewHolder.imageContainerFl.setCornerRadius(getDimension(R.dimen.article_image_corner_radius))

                Picasso.with(applicationContext)
                    .load(itemModel.imageUrl)
                    .resize(
                        getDimensionPixelSize(R.dimen.article_image_size),
                        getDimensionPixelSize(R.dimen.article_image_size)
                    )
                    .centerCrop()
                    .into(viewHolder.imageIv)
            }
        }
    }


    fun setOnItemClickListener(viewHolder : ViewHolder?, onItemClickListener : OnItemClickListener<ArticleItem>?) {
        viewHolder?.itemView?.setOnItemClickListener(this, 0, onItemClickListener)
    }


    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }


    override fun getTrackKey() : Int {
        return itemModel.id
    }


    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Article>(itemView) {

        val titleTv = itemView.findViewById<TextView>(R.id.titleTv)
        val contentTv = itemView.findViewById<TextView>(R.id.contentTv)
        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val imageContainerFl = itemView.findViewById<TAFrameLayout>(R.id.imageContainerFl)

    }


    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.article_item_layout

    }


}