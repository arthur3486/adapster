package com.arthurivanets.sample

import android.graphics.Color
import android.widget.ListView
import com.arthurivanets.sample.adapters.model.ArticleItem
import com.arthurivanets.sample.adapters.SimpleListViewAdapter
import com.arthurivanets.adapster.listeners.OnDatasetChangeListener
import com.arthurivanets.adapster.model.BaseItem
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TrackableListViewAdapterTests {


    lateinit var activity : ListViewBasedDemoActivity
    lateinit var listView : ListView
    lateinit var adapter : SimpleListViewAdapter


    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(ListViewBasedDemoActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        listView = activity.findViewById(R.id.listView)
        adapter = (listView.adapter as SimpleListViewAdapter)
        adapter.clear()
    }


    @Test
    fun testInitialItemCount() {
        assertEquals(adapter.itemCount, 0)
    }


    @Test
    fun testItemAddition() {
        adapter.clear()

        // preparing the dummy data
        val item1a = ArticleItem(SampleModel(1, "Item 1a"))
        val item1b = ArticleItem(SampleModel(1, "Item 1b"))
        val previousItemCount = adapter.itemCount

        // adding the first item into the dataset and verifying the
        adapter.addItem(item1a)

        val newItemCount = adapter.itemCount

        assertEquals((adapter.getItem(adapter.indexOf(item1a)) as ArticleItem?)!!.itemModel.title, item1a.itemModel.title)
        assertNotSame(previousItemCount, newItemCount)
        assertTrue(adapter.contains(item1a))

        //
        adapter.addItem(item1b)

        assertNotSame((adapter.getItem(adapter.indexOf(item1a)) as ArticleItem?)!!.itemModel.title, item1b.itemModel.title)

        assertNotSame(previousItemCount, newItemCount)
        assertEquals(adapter.itemCount, newItemCount)
    }


    @Test
    fun testItemAdditionAtValidIndices() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))

        //
        adapter.addItem(0, item1)

        assertEquals(adapter.firstItem, item1)

        //
        adapter.addItem(1, item2)

        assertEquals(adapter.getItem(1), item2)

        //
        adapter.addItem(0, item3)

        assertEquals(adapter.firstItem, item3)
    }


    @Test(expected = IndexOutOfBoundsException::class)
    fun testItemAdditionAtTooLowIndex() {
        adapter.clear()
        adapter.addItem(-1, ArticleItem(SampleModel(1, "Item 1")))
    }


    @Test(expected = IndexOutOfBoundsException::class)
    fun testItemAdditionAtTooHighIndex() {
        adapter.clear()
        adapter.addItem(1, ArticleItem(SampleModel(1, "Item 1")))
    }


    @Test
    fun testSameItemAddition() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val previousItemCount = adapter.itemCount

        adapter.addItem(item1)

        val newItemCount = adapter.itemCount

        assertNotSame(previousItemCount, newItemCount)
        assertTrue(adapter.contains(item1))

        //
        adapter.addItem(item1)
        adapter.addItem(item1)

        //
        assertTrue(adapter.contains(item1))
        assertNotSame(previousItemCount, newItemCount)
        assertEquals(adapter.itemCount, newItemCount)
    }


    @Test
    fun testSameItemAdditionWithUpdating() {
        adapter.clear()

        // preparing the dummy data
        val item1a = ArticleItem(SampleModel(1, "Item 1a"))
        val item1b = ArticleItem(SampleModel(1, "Item 1b"))
        val previousItemCount = adapter.itemCount

        //
        adapter.addOrUpdateItem(item1a)

        val newItemCount = adapter.itemCount

        assertEquals((adapter.getItem(adapter.indexOf(item1a)) as ArticleItem?)!!.itemModel.title, item1a.itemModel.title)
        assertNotSame(previousItemCount, newItemCount)
        assertTrue(adapter.contains(item1a))

        //
        adapter.addOrUpdateItem(item1b)

        assertEquals((adapter.getItem(adapter.indexOf(item1a)) as ArticleItem?)!!.itemModel.title, item1b.itemModel.title)
        assertNotSame(previousItemCount, newItemCount)
        assertEquals(adapter.itemCount, newItemCount)
    }


    @Test
    fun testItemAdditionWithUpdatingAtValidIndices() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))
        val item3b = ArticleItem(SampleModel(3, "Item 3b"))

        //
        adapter.addOrUpdateItem(0, item1)

        assertEquals(adapter.firstItem, item1)

        //
        adapter.addOrUpdateItem(1, item2)

        assertEquals(adapter.getItem(1), item2)

        //
        adapter.addOrUpdateItem(0, item3)

        assertEquals(adapter.firstItem, item3)

        //
        adapter.addOrUpdateItem(0, item3b)

        assertEquals((adapter.firstItem as ArticleItem?)!!.itemModel.title, item3b.itemModel.title)
    }


    @Test(expected = IndexOutOfBoundsException::class)
    fun testItemAdditionWithUpdatingAtTooLowIndex() {
        adapter.clear()
        adapter.addOrUpdateItem(-1, ArticleItem(SampleModel(1, "Item 1")))
    }


    @Test(expected = IndexOutOfBoundsException::class)
    fun testItemAdditionWithUpdatingAtTooHighIndex() {
        adapter.clear()
        adapter.addOrUpdateItem(1, ArticleItem(SampleModel(1, "Item 1")))
    }


    @Test(expected = IllegalStateException::class)
    fun testHeaderAdding() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val headerItem1 = HeaderItem(Info(Color.RED, "Header Item 1"))
        val headerItem2 = HeaderItem(Info(Color.RED, "Header Item 2"))

        //
        adapter.addItem(item1)
        adapter.addHeader(headerItem1)

        assertEquals(adapter.firstItem, headerItem1)

        //
        adapter.addHeader(headerItem2)
    }


    @Test
    fun testHeaderRemoval() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val headerItem = HeaderItem(Info(Color.RED, "Header Item"))

        //
        adapter.addItem(item1)
        adapter.addHeader(headerItem)

        //
        assertEquals(adapter.firstItem, headerItem)

        //
        adapter.removeHeader()

        //
        assertEquals(adapter.firstItem, item1)
    }


    @Test(expected = IllegalStateException::class)
    fun testFooterAdding() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val footerItem1 = FooterItem(Info(Color.GREEN, "Footer Item 1"))
        val footerItem2 = FooterItem(Info(Color.GREEN, "Footer Item 2"))

        //
        adapter.addItem(item1)
        adapter.addFooter(footerItem1)

        assertEquals(adapter.lastItem, footerItem1)

        //
        adapter.addFooter(footerItem2)
    }


    @Test
    fun testFooterRemoval() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val footerItem = FooterItem(Info(Color.RED, "Footer Item"))

        //
        adapter.addItem(item1)
        adapter.addFooter(footerItem)

        //
        assertEquals(adapter.lastItem, footerItem)

        //
        adapter.removeFooter()

        //
        assertEquals(adapter.lastItem, item1)
    }


    @Test
    fun testItemUpdating() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item1b = ArticleItem(SampleModel(1, "Item 1b"))

        //
        adapter.addItem(item1)

        assertEquals((adapter.firstItem as ArticleItem?)!!.itemModel.title, item1.itemModel.title)

        //
        adapter.updateItemWith(item1b)

        assertEquals((adapter.firstItem as ArticleItem?)!!.itemModel.title, item1b.itemModel.title)
    }


    @Test
    fun testItemUpdatingByIndex() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item1b = ArticleItem(SampleModel(1, "Item 1b"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))

        //
        adapter.addItem(item1)
        adapter.addItem(item2)

        assertEquals((adapter.firstItem as ArticleItem?)!!.itemModel.title, item1.itemModel.title)

        //
        adapter.updateItemWith(item1b)

        assertEquals((adapter.firstItem as ArticleItem?)!!.itemModel.title, item1b.itemModel.title)
    }


    @Test
    fun testItemRemovalByItem() {
        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))
        val items = mutableListOf< BaseItem <*, *, *>>(item1, item2, item3)

        //
        adapter.items = items

        //
        assertEquals(adapter.items, items)

        //
        adapter.deleteItem(item1)

        assertNotSame(adapter.firstItem, item1)
        assertEquals(adapter.firstItem, item2)

        //
        adapter.deleteItem(item2)

        assertNotSame(adapter.firstItem, item2)
        assertEquals(adapter.firstItem, item3)

        //
        adapter.deleteItem(item3)

        assertEquals(adapter.itemCount, 0)
    }


    @Test
    fun testItemRemovalByIndex() {
        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))
        val items = mutableListOf< BaseItem <*, *, *>>(item1, item2, item3)

        //
        adapter.items = items

        //
        assertEquals(adapter.items, items)

        //
        adapter.deleteItem(0)

        assertNotSame(adapter.firstItem, item1)
        assertEquals(adapter.firstItem, item2)

        //
        adapter.deleteItem(1)

        assertNotSame(adapter.firstItem, item3)
        assertEquals(adapter.firstItem, item2)

        //
        adapter.deleteItem(0)

        assertEquals(adapter.itemCount, 0)
    }


    @Test
    fun testItemFetching() {
        adapter.clear()

        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))

        assertEquals(adapter.getItem(0), null)

        // adding the dummy data into the dataset and verifying that the proper item is fetched
        adapter.addItem(item1)

        assertEquals(adapter.getItem(0), item1)
    }


    @Test
    fun testItemIndexLookup() {
        adapter.clear()

        // preparing the dummy data and attempting to retrieve the dataset index for it
        val item1 = ArticleItem(SampleModel(1, "Item 1"))

        assertEquals(adapter.indexOf(item1), -1)

        // adding the dummy data into the actual dataset and verifying its index
        adapter.addItem(item1)

        assertEquals(adapter.indexOf(item1), 0)
    }


    @Test
    fun testItemPresenceLookup() {
        adapter.clear()

        // preparing the dummy data and verifying if it's present within the dataset
        val item1 = ArticleItem(SampleModel(1, "Item 1"))

        assertFalse(adapter.contains(item1))

        // adding the dummy data into the dataset and verifying its presence
        adapter.addItem(item1)

        assertTrue(adapter.contains(item1))
    }


    @Test
    fun testDataSetSetting() {
        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))
        val items = mutableListOf< BaseItem <*, *, *>>(item1, item2, item3)

        // setting the new dummy dataset
        adapter.items = items

        // verifying if the new dataset (and all of its items) are
        // indeed the ones that we just set
        assertEquals(adapter.items, items)

        for(i in 0 until items.size) {
            assertEquals(adapter.getItem(i), items[i])
        }
    }


    @Test
    fun testDataSetClearing() {
        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))

        // adding the dummy item into the dataset and verifying if it was indeed added
        adapter.addItem(item1)

        assertTrue(adapter.itemCount > 0)

        // clearing the dataset and verifying if it was actually cleared
        adapter.clear()

        assertEquals(adapter.itemCount, 0)
    }


    @Test
    fun testDatasetChangeListener() {
        // preparing the dummy data
        val item1 = ArticleItem(SampleModel(1, "Item 1"))
        val item2 = ArticleItem(SampleModel(2, "Item 2"))
        val item3 = ArticleItem(SampleModel(3, "Item 3"))
        val item4 = ArticleItem(SampleModel(4, "Item 4"))
        val item4b = ArticleItem(SampleModel(4, "Item 4b"))
        val items = mutableListOf< BaseItem <*, *, *>>(item1, item2, item3)

        // assigning a Dataset Change Listener and performing all the observable operations sequentially
        adapter.removeAllOnDatasetChangeListeners()
        adapter.addOnDatasetChangeListener(object : OnDatasetChangeListener<MutableList<BaseItem<*, *, *>>, BaseItem<*, *, *>> {

            override fun onItemAdded(dataset : MutableList<BaseItem<*, *, *>>, item : BaseItem<*, *, *>) {
                assertEquals(item, item4)
            }

            override fun onItemUpdated(dataset : MutableList<BaseItem<*, *, *>>, item : BaseItem<*, *, *>) {
                assertEquals(item, item1)
            }

            override fun onItemReplaced(dataset : MutableList<BaseItem<*, *, *>>, oldItem : BaseItem<*, *, *>, newItem : BaseItem<*, *, *>) {
                assertEquals(oldItem, item4)
                assertEquals(newItem, item4b)
            }

            override fun onItemDeleted(dataset : MutableList<BaseItem<*, *, *>>, item : BaseItem<*, *, *>) {
                assertEquals(item, item1)
            }

            override fun onDatasetReplaced(newDataset : MutableList<BaseItem<*, *, *>>) {
                assertEquals(items, newDataset)
            }

            override fun onDatasetCleared(dataset : MutableList<BaseItem<*, *, *>>) {
                assertTrue(dataset.isEmpty())
            }

        })
        adapter.items = items
        adapter.addItem(item4)
        adapter.updateItem(item1)
        adapter.updateItemWith(item4b)
        adapter.deleteItem(0)
        adapter.clear()
        adapter.removeAllOnDatasetChangeListeners()
    }


}