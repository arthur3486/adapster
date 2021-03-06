/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

package com.arthurivanets.adapster.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arthurivanets.adapster.Adapter;
import com.arthurivanets.adapster.listeners.OnDatasetChangeListener;
import com.arthurivanets.adapster.listeners.OnItemClickListener;
import com.arthurivanets.adapster.markers.SupportsFooter;
import com.arthurivanets.adapster.markers.SupportsHeader;
import com.arthurivanets.adapster.model.BaseItem;
import com.arthurivanets.adapster.model.markers.Footer;
import com.arthurivanets.adapster.model.markers.Header;
import com.arthurivanets.adapster.markers.ItemResources;
import com.arthurivanets.adapster.util.Preconditions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A base class for the implementation of any RecyclerView adapter.
 *
 * @param <IT> the dataset item type
 * @param <VH> the item view holder type
 * @author arthur3486
 */
public abstract class BaseRecyclerViewAdapter<IT extends BaseItem, VH extends BaseItem.ViewHolder<?>> extends RecyclerView.Adapter<VH> implements Adapter<IT>,
        SupportsHeader<VH>, SupportsFooter<VH> {


    private Context mContext;
    private RecyclerView mRecyclerView;

    private IT mItem;
    private List<IT> mItems;

    private final LayoutInflater mLayoutInflater;
    private final Set<OnDatasetChangeListener<List<IT>, IT>> mOnDatasetChangeListeners;

    private OnItemClickListener<Header<VH>> mOnHeaderClickListener;
    private OnItemClickListener<Footer<VH>> mOnFooterClickListener;




    public BaseRecyclerViewAdapter(@NonNull Context context, @NonNull List<IT> items) {
        Preconditions.nonNull(context);
        Preconditions.nonNull(items);

        mContext = context;
        mItems = items;
        mLayoutInflater = LayoutInflater.from(context);
        mOnDatasetChangeListeners = new HashSet<>();
    }




    @Override
    public final void addItem(@NonNull IT item) {
        addItem(item, true);
    }




    @Override
    public final void addItem(@NonNull IT item, boolean notifyAboutTheChange) {
        Preconditions.nonNull(item);

        addItem(mItems.size(), item, notifyAboutTheChange);
    }




    @Override
    public final void addItem(int position, @NonNull IT item) {
        addItem(position, item, true);
    }




    @Override
    public final void addOrUpdateItem(@NonNull IT item) {
        addOrUpdateItem(item, true);
    }




    @Override
    public final void addOrUpdateItem(@NonNull IT item, boolean notifyAboutTheChange) {
        Preconditions.nonNull(item);

        addOrUpdateItem(mItems.size(), item, notifyAboutTheChange);
    }




    @Override
    public final void addOrUpdateItem(int position, @NonNull IT item) {
        addOrUpdateItem(position, item, true);
    }




    @Override
    public final void updateItem(@NonNull IT item) {
        final int itemIndex = indexOf(item);

        if(itemIndex != -1) {
            updateItem(itemIndex);
        }
    }




    @Override
    public final void updateItem(int position) {
        Preconditions.withinBoundsExclusive(position, mItems);

        notifyItemChanged(position);
        notifyItemUpdated(getItem(position));
    }




    /**
     * Updates the specified range of items.
     * (Notifies the adapter about the need to perform the UI update on the specified items)
     *
     * @param startPosition
     * @param itemCount
     */
    public final void updateItems(int startPosition, int itemCount) {
        Preconditions.withinBoundsExclusive(startPosition, mItems);

        notifyItemRangeChanged(startPosition, itemCount);
    }




    @Override
    public final void updateItemWith(@NonNull IT item) {
        updateItemWith(item, true);
    }




    @Override
    public final void updateItemWith(@NonNull IT item, boolean notifyAboutTheChange) {
        final int itemIndex = indexOf(item);

        if(itemIndex != -1) {
            updateItemWith(itemIndex, item, notifyAboutTheChange);
        }
    }




    @Override
    public final void updateItemWith(int position, @NonNull IT item) {
        updateItemWith(position, item, true);
    }




    @Override
    public abstract void updateItemWith(int position, @NonNull IT item, boolean notifyAboutTheChange);




    @Override
    public final void deleteItem(@NonNull IT item) {
        final int itemIndex = indexOf(item);

        if(itemIndex != -1) {
            deleteItem(itemIndex);
        }
    }




    /**
     *
     * <br>
     *      Implements the default functionality. For more complex presence checks
     *      (like with a dedicated HashMap for Item tracking), please make sure
     *      to override this method and add the necessary functionality.
     * <br>
     *
     */
    @Override
    public boolean contains(@NonNull IT item) {
        Preconditions.nonNull(item);
        return mItems.contains(item);
    }




    /**
     *
     * <br>
     *      Implements the default functionality. For more complex index determining
     *      (like with a dedicated HashMap for Item tracking), please make sure
     *      to override this method and add the necessary functionality.
     * <br>
     *
     */
    @Override
    public int indexOf(@NonNull IT item) {
        Preconditions.nonNull(item);
        return mItems.indexOf(item);
    }




    @Override
    public final int lastIndex() {
        return (mItems.size() - 1);
    }




    /**
     * Notifies the Dataset Change Observers about the addition of a new item.
     *
     * @param item the added item
     */
    protected final void notifyItemAdded(@NonNull IT item) {
        Preconditions.nonNull(item);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onItemAdded(mItems, item);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the item update.
     *
     * @param item the updated item
     */
    protected final void notifyItemUpdated(@NonNull IT item) {
        Preconditions.nonNull(item);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onItemUpdated(mItems, item);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the replacement of the item.
     *
     * @param oldItem the old item that got replaced
     * @param newItem the new replacement item
     */
    protected final void notifyItemReplaced(@NonNull IT oldItem, @NonNull IT newItem) {
        Preconditions.nonNull(oldItem);
        Preconditions.nonNull(newItem);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onItemReplaced(mItems, oldItem, newItem);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the deletion of the item.
     *
     * @param item the deleted item
     */
    protected final void notifyItemDeleted(@NonNull IT item) {
        Preconditions.nonNull(item);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onItemDeleted(mItems, item);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the Dataset size changes.
     *
     * @param oldSize
     * @param newSize
     */
    protected final void notifyDatasetSizeChanged(int oldSize, int newSize) {
        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onDatasetSizeChanged(oldSize, newSize);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the replacement of the dataset.
     *
     * @param newDataset the new replacement dataset
     */
    protected final void notifyDatasetReplaced(@NonNull List<IT> newDataset) {
        Preconditions.nonNull(newDataset);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onDatasetReplaced(newDataset);
        }
    }




    /**
     * Notifies the Dataset Change Observers about the clearing of the dataset.
     *
     * @param dataset the cleared dataset
     */
    protected final void notifyDatasetCleared(@NonNull List<IT> dataset) {
        Preconditions.nonNull(dataset);

        for(OnDatasetChangeListener<List<IT>, IT> listener : mOnDatasetChangeListeners) {
            listener.onDatasetCleared(dataset);
        }
    }




    /**
     *  You'll need to use this method instead of the <strong>.notifyDataSetChanged()</strong>
     *  in most of the cases, as this method updates only "dynamic" items,
     *  while the <strong>Header</strong> and <strong>Footer</strong>(if either of them is present)
     *  remain untouched.<strong>(Acts as an updateItem() method applied to the whole dataset)</strong>
     */
    public final void notifyItemsChanged() {
        final int itemCount = getItemCount();

        // if the dataset is empty, returning(as there's no data to be processed)
        if(itemCount == 0) {
            return;
        }

        // preparing the initial indices
        int startIndex = 0;
        int endIndex = (itemCount - 1);

        // adjusting the start index(if there's a Header present),
        // as we don't need to touch the Header itself.
        if(getItem(startIndex) instanceof Header) {
            startIndex++;
        }

        // adjusting the end index(if there's a Footer present),
        // as we don't need to touch the Footer itself.
        if(getItem(endIndex) instanceof Footer) {
            endIndex--;
        }

        // calculating the final(to be updated) item count
        final int adjustedItemCount = (endIndex - startIndex + 1);

        // updating the necessary("dynamic") items(if necessary)
        if(adjustedItemCount > 0) {
            updateItems(startIndex, adjustedItemCount);
        }
    }




    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }




    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = null;
    }




    @Override
    public final VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent, viewType, mItem);
    }




    /**
     *  Performs the Item View(and ViewHolder) initialization.
     */
    @SuppressWarnings("unchecked")
    protected VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType, @NonNull IT item) {
        return (VH) item.init(this, parent, mLayoutInflater, getResources());
    }




    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final IT item = getItem(position);

        // performing the data binding
        item.bind(this, holder, getResources());

        // allowing the extenders to assign listeners(if necessary)
        assignListeners(holder, position, item);
    }




    /**
     *  Gets called when it's the right time to assign the Listeners to the
     *  corresponding item. Override it only if you need to provide the Listener settings functionality.
     */
    @SuppressWarnings("unchecked")
    @CallSuper
    protected void assignListeners(@NonNull VH holder, int position, @NonNull IT item) {
        //assigning the default listeners
        if(item instanceof Header) {
            ((Header<VH>) item).setOnItemClickListener(holder, mOnHeaderClickListener);
        } else if(item instanceof Footer) {
            ((Footer<VH>) item).setOnItemClickListener(holder, mOnFooterClickListener);
        }
    }




    @Override
    public final void addOnDatasetChangeListener(@NonNull OnDatasetChangeListener<List<IT>, IT> onDatasetChangeListener) {
        Preconditions.nonNull(onDatasetChangeListener);

        mOnDatasetChangeListeners.add(onDatasetChangeListener);
    }




    @Override
    public final void removeOnDatasetChangeListener(@NonNull OnDatasetChangeListener<List<IT>, IT> onDatasetChangeListener) {
        Preconditions.nonNull(onDatasetChangeListener);

        mOnDatasetChangeListeners.remove(onDatasetChangeListener);
    }




    @Override
    public final void removeAllOnDatasetChangeListeners() {
        mOnDatasetChangeListeners.clear();
    }




    @Override
    public final void setOnHeaderClickListener(OnItemClickListener<Header<VH>> onHeaderClickListener) {
        mOnHeaderClickListener = onHeaderClickListener;
    }




    @Override
    public final void setOnFooterClickListener(OnItemClickListener<Footer<VH>> onFooterClickListener) {
        mOnFooterClickListener = onFooterClickListener;
    }




    @CallSuper
    @Override
    public void setItems(@NonNull List<IT> items) {
        setItems(items, true);
    }




    @CallSuper
    @Override
    public void setItems(@NonNull List<IT> items, boolean notifyAboutTheChange) {
        Preconditions.nonNull(items);

        final int itemCount = getItemCount();
        mItems = items;

        if(notifyAboutTheChange) {
            notifyDataSetChanged();
        }

        notifyDatasetReplaced(mItems);
        notifyDatasetSizeChanged(itemCount, getItemCount());
    }




    /**
     *  Sets the underlying dataset.
     *  (Uses the specified DiffUtils.Callback to calculate the item changes and dispatch the updates to the adapter)
     *
     *  @param items
     *  @param callback
     */
    public abstract void setItems(@NonNull List<IT> items, @NonNull DiffUtil.Callback callback);




    @NonNull
    public final Context getContext() {
        return mContext;
    }




    @Nullable
    public final RecyclerView getRecyclerView() {
        return mRecyclerView;
    }




    @NonNull
    @Override
    public final List<IT> getItems() {
        return mItems;
    }




    @Override
    public final int getItemViewType(int position) {
        mItem = getItem(position);
        return getItemViewType(position, mItem);
    }




    public abstract int getItemViewType(int position, IT item);




    @Nullable
    @Override
    public final IT getItem(int position) {
        return (((position >= 0) && (position < getItemCount())) ? mItems.get(position) : null);
    }




    @Override
    public final IT getFirstItem() {
        return getItem(0);
    }




    @Override
    public final IT getLastItem() {
        return getItem(lastIndex());
    }




    @Override
    public long getItemId(int position) {
        final long itemId = getItem(position).getId();
        return ((itemId != BaseItem.NO_ID) ? itemId : position);
    }




    /**
     * Implements the default Item Count retrieval with the default NPE check.
     *
     * @return If <strong>dataset</strong> is NOT NULL -> dataset.size(), else 0
     */
    @Override
    public final int getItemCount() {
        return mItems.size();
    }




    /**
     * Retrieves the reusable {@link ItemResources} associated with the current adapter.
     *
     * @return the reusable {@link ItemResources} associated with the current adapter, or <strong>null</strong> if no resources have been associated.
     */
    @Nullable
    public ItemResources getResources() {
        return null;
    }




    /**
     *  Calculates the difference between the datasets and dispatches the calculated data to the adapter.
     *
     *  @param callback difference calculation helper
     *  @param beforeDispatch a callback to be called in-between the calculation of the difference
     *                        and the actual dispatching of the updates to the adapter
     */
    protected final void applyDiffUtils(DiffUtil.Callback callback, Runnable beforeDispatch) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        beforeDispatch.run();

        result.dispatchUpdatesTo(this);
    }




}
