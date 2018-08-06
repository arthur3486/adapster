# Adapster 

> ***Android library designed to enrich and make your RecyclerView adapters more SOLID***

Adapster will help you make your RecyclerView adapters more manageable and overall enrich your RecyclerView epxerience.

[ ![Download](https://api.bintray.com/packages/arthurimsacc/adapster/adapster/images/download.svg) ](https://bintray.com/arthurimsacc/adapster/adapster/_latestVersion)
![](https://travis-ci.com/arthur3486/adapster.svg?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-Android-green.svg)](http://developer.android.com/index.html)

## Contents

* [Demo](#demo-youtube)
* [Getting Started](#getting-started)
* [Basic RecyclerView-based Implementation](#basic-recyclerview-based-implementation)
* [Basic ListView-based Implementation](#basic-listview-based-implementation)
* [Advanced Use](#advanced-use)
* [Contribution](#contribution)
* [Hall of Fame](#hall-of-fame)
* [License](#license)

## Demo (YouTube)

[![YouTube Video](https://i.ytimg.com/vi/iPisnhls9zc/hqdefault.jpg)](https://www.youtube.com/watch?v=iPisnhls9zc)

## Getting Started

1. Make sure that you've added the `jcenter()` repository to your top-level `build.gradle` file.

````groovy
buildscript {
    //...
    repositories {
        //...
        jcenter()
    }
    //...
}
````

2. Add the library dependency to your module-level `build.gradle` file. 
> ***Latest version:*** [ ![Download](https://api.bintray.com/packages/arthurimsacc/adapster/adapster/images/download.svg) ](https://bintray.com/arthurimsacc/adapster/adapster/_latestVersion)

````groovy
ext {
    //...
    adapsterLibraryVersion = "1.0.1"
}

dependencies {
    //...
    implementation "com.arthurivanets.adapster:adapster:$adapsterLibraryVersion"
}
````

3. Enable the **jetifier** and **androidX** support in the top-level `gradle.properties`

````groovy
//...
android.enableJetifier=true
android.useAndroidX=true
//....
````

4. Proceed with the implementation of your own adapter. 
> ***See: [Basic RecyclerView-based Implementation](#basic-recyclerview-based-implementation) and [Basic ListView-based Implementation](#basic-listview-based-implementation)***

## Basic RecyclerView-based Implementation

Implementation of a basic RecyclerView-based concept involves 3 main steps - creation of concrete Item classes, creation of the adapter, and binding of the adapter to the RecyclerView.

Let's implement a basic RecyclerView-based concept by following the steps listed above:

1. Creation of a concrete Item class that extends the [`BaseItem<IM, VH, IR>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/model/BaseItem.java) base class.

- Creating a model class `Article`

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
data class Article(val id : Int,
                   val title : String,
                   val text : String,
                   val imageUrl : String = "") {

    val hasImage : Boolean
        get() = !imageUrl.isBlank()

}
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class Article {

	private int id;
	private String title;
	private String text;
	private String imageUrl;

	public Article() {
		this.id = -1;
		this.title = "";
		this.text = "";
		this.imageUrl = "";
	}

	// Setters and Getters...

	public final boolean hasImage() {
		return !TextUtils.isEmpty(this.imageUrl);
	}

}
````

</p></details><br>

- Creating the `ArticleItem` item class for the `Article` model

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
class ArticleItem(itemModel : Article) : BaseItem<Article, ArticleItem.ViewHolder, ItemResources>(itemModel), Trackable<Int> {

    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
          // inflate (or create) your item view here and create a corresponding ViewHolder
          // then return the created ViewHolder
    }

    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        // bind the data here
        // (use the itemModel associated with this item to access the data)
    }

    override fun getLayout() : Int {
        // return a unique id, which will be used as a View Type for this item
        // (you can use the item view layout id if this item's view is not going
        // to be modified dynamically by adding new views to it, otherwise you will
        // have to compose your own id to properly distinguish the item's view within your adapter)
        return MAIN_LAYOUT_ID
    }

    override fun getTrackKey() : Int {
        // use the model's unique id to prevent the duplicates within the Adapter
        // (this step is optional and is enabled by the implementation of the Trackable<KeyType> interface)
        return itemModel.id
    }

    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Article>(itemView) {

        // look up (or initialize) your views here...

    }

    companion object {

        // provide a global unique base id for this item 
        // (it will help you identify the item when assigning the listeners in the adapter)
        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.article_item_layout

    }

}
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class ArticleItem extends BaseItem<Article, ArticleItem.ViewHolder, ItemResources> implements Trackable<Integer> {

    // provide a global unique base id for this item 
    // (it will help you identify the item when assigning the listeners in the adapter)
    public static final int MAIN_LAYOUT_ID = R.layout.article_item_layout;

    public ArticleItem(Article itemModel) {
        super(itemModel);
    }

    @Override
    public ViewHolder init(Adapter<? extends Item> adapter,
                           ViewGroup parent,
                           LayoutInflater inflater,
                           ItemResources resources) {
          // inflate (or create) your item view here and create a corresponding ViewHolder
          // then return the created ViewHolder
    }

    @Override
    public void bind(Adapter<? extends Item> adapter,
                     ViewHolder viewHolder,
                     ItemResources resources) {
        super.bind(adapter, viewHolder, resources);

        // bind the data here
        // (use the itemModel associated with this item to access the data)
    }
    
    @Override
    public int getLayout() {
        // return a unique id, which will be used as a View Type for this item
        // (you can use the item view layout id if this item's view is not going
        // to be modified dynamically by adding new views to it, otherwise you will
        // have to compose your own id to properly distinguish the item's view within your adapter)
        return MAIN_LAYOUT_ID;
    }

    @Override
    public int getTrackKey() {
        // use the model's unique id to prevent the duplicates within the Adapter
        // (this step is optional and is enabled by the implementation of the Trackable<KeyType> interface)
        return getItemModel().getId();
    }

    public static class ViewHolder extends BaseItem.ViewHolder<Article> {

	    public ViewHolder(View itemView) {
		    super(itemView);

		    // look up (or initialize) your views here...
	    }

    }

}
````

</p></details><br>

2. Creation of the adapter that extends the [`TrackableRecyclerViewAdapter<KT, IT, VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/recyclerview/TrackableRecyclerViewAdapter.java) base class.

- Creating the `ArticlesRecyclerViewAdapter`

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
class ArticlesRecyclerViewAdapter(context : Context,
                                  items : MutableList<ArticleItem>) : TrackableRecyclerViewAdapter<Long, ArticleItem, ArticleItem.ViewHolder>(context, items) {

    var onArticleItemClickListener : OnItemClickListener<ArticleItem>? = null

    init {
        setHasStableIds(true)
    }

    override fun assignListeners(holder : ArticleItem.ViewHolder, position : Int, item : ArticleItem) {
        super.assignListeners(holder, position, item)

        item.setOnItemClickListener(holder, onArticleItemClickListener)
    }

}
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class ArticlesRecyclerViewAdapter extends TrackableRecyclerViewAdapter<Long, ArticleItem, ArticleItem.ViewHolder> {

    private OnItemClickListener<ArticleItem> onArticleItemClickListener;

    public ArticlesRecyclerViewAdapter(Context context, List<ArticleItem> items) {
        super(context, items);
        setHasStableIds(true);
    }

    @Override
    public void assignListeners(ArticleItem.ViewHolder holder, int position, ArticleItem item) {
        super.assignListeners(holder, position, item);

        item.setOnItemClickListener(holder, onArticleItemClickListener);
    }

    public final void setOnItemClickListener(OnItemClickListener<ArticleItem> onArticleItemClickListener) {
        this.onArticleItemClickListener = onArticleItemClickListener;
    }

}
````

</p></details><br>

3. Instantiation of the created adapter and its binding to the RecyclerView.

- Instantiating the `ArticlesRecyclerViewAdapter`, setting the Adapter-specific listeners, and binding the adapter to the RecyclerView

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
//...
private var items = ArrayList<ArticleItem>()
private lateinit var adapter : ArticlesRecyclerViewAdapter

//...
private fun initRecyclerView() {
    //...
    adapter = ArticlesRecyclerViewAdapter(this, items)
    adapter.onArticleItemClickListener = OnItemClickListener { view, item, position ->
        // handle the article item click
    }

    //...
    recyclerView.adapter = adapter
}
//...
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
//...
private List<ArticleItem> items = new ArrayList<>();
private RecyclerView recyclerView;
private ArticlesRecyclerViewAdapter adapter;

//...
private void initRecyclerView() {
    //....
    recyclerView = findViewById(R.id.recyclerView);

    //...
    adapter = new ArticlesRecyclerViewAdapter(this, items);
    adpater.setOnArticleItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClicked(View view, ArticleItem item, int position) {
            // handle the article item click
        }
    });

    //...
    recyclerView.setAdapter(adapter);
}
````

</p></details>

## Basic ListView-based Implementation

Implementation of a basic ListView-based concept is identical to the [Basic RecyclerView-based Implementation](#basic-recyclerview-based-implementation), with the exception of one thing - the adapter implementation.

In the ListView-based concept your adapter should be provided with a little more information about the nature of the items it's handling, such as the exact View Type Count the adapter is going to handle, and Item View Type for a given adapter position (This additional information is only required in cases when you have more than one View Type associated with the Adapter).

Here's the implementation of the `ArticlesListViewAdapter`

<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
class ArticlesListViewAdapter(context : Context,
                              items : MutableList<ArticleItem>) : TrackableListViewAdapter<Long, ArticleItem, ArticleItem.ViewHolder>(context, items) {

    var onItemClickListener : OnItemClickListener<ArticleItem>? = null

    override fun assignListeners(holder : ArticleItem.ViewHolder, position : Int, item : ArticleItem) {
        super.assignListeners(holder, position, item)

        item.setOnItemClickListener(holder, onItemClickListener)
    }

}
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class ArticlesListViewAdapter extends TrackableListViewAdapter<Long, ArticleItem, ArticleItem.ViewHolder> {

    private OnItemClickListener<ArticleItem> onItemClickListener;

    public ArticlesListViewAdapter(Context context, List<ArticleItem> items) {
        super(context, items);
        setHasStableIds(true);
    }

    @Override
    public void assignListeners(ArticleItem.ViewHolder holder, int position, ArticleItem item) {
        super.assignListeners(holder, position, item);

        item.setOnItemClickListener(holder, onItemClickListener);
    }

    public final void setOnItemClickListener(OnItemClickListener<ArticleItem> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
````

</p></details>

## Advanced Use

- **Header and Footer Support**
<br>To add a Header and/or Footer to your RecyclerView you need to create an Item class that extends the [`BaseItem<IM, VH, IR>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/model/BaseItem.java) and implement the marker interface [`Header<VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/model/markers/Header.java) (or [`Footer<VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/model/markers/Footer.java)) on it (but not both at the same time). Thereafter you will be able to provide the implementation for the interface methods.

The implementation of the aforementioned marker interfaces will allow the adapter to properly distinguish and handle the items; the Header Item will always be placed at the top, while the Footer - at the bottom.

**IMPORTANT**: To be able to properly utilize this functionality (Header and/or Footer support) you must first make sure that the adapter you're using has that support provided to you. An adapter that supports Header and/or Footer must implement the [`SupportsHeader<VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsHeader.java) and/or [`SupportsFooter<VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsFooter.java) interfaces and the corresponding interface methods. There's no need to worry about this nuance if you're using the library-provided adapter implementations (such as [`TrackableRecyclerViewAdapter<KT, IT, VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/recyclerview/TrackableRecyclerViewAdapter.java) and [`TrackableListViewAdapter<KT, IT, VH>`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/listview/TrackableListViewAdapter.java)), as they do provide the support for both the Header and Footer; otherwise, if you decide to implement your own adapter based on the contracts provided by the library you should consider implementing the aforementioned interfaces to provide the desired Header and/or Footer support.

Adapters that have the Header and/or Footer support provide the following list of convenience-methods:
<br>**Header-related**
<br>[`addHeader(Header<VH>)`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsHeader.java#L41)
<br>[`removeHeader()`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsHeader.java#L46)
<br>[`setOnHeaderClickListener(OnItemClickListener<Header<VH>>)`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsHeader.java#L53)
<br>**Footer-related**
<br>[`addFooter(Footer<VH>)`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsFooter.java#L41)
<br>[`removeFooter()`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsFooter.java#L46)
<br>[`setOnFooterClickListener(OnItemClickListener<Footer<VH>>)`](https://github.com/arthur3486/adapster/blob/master/adapster/src/main/java/com/arthurivanets/adapster/markers/SupportsFooter.java#L53)
<br>These methods will help you manage the Header and/or Footer within your adapter.

Here's an example of how to create a Header Item and what changes need to take place in the Adapter.
- Creating a `TopicHeaderItem`
<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
class TopicItem(itemModel : Topic) : BaseItem<Topic, TopicItem.ViewHolder, ItemResources>(itemModel),
        Header<BaseItem.ViewHolder<*>> {

    override fun init(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      parent : ViewGroup,
                      inflater : LayoutInflater,
                      resources : ItemResources?) : ViewHolder {
        return ViewHolder(inflater.inflate(
            MAIN_LAYOUT_ID,
            parent,
            false
        ))
    }

    override fun bind(adapter : Adapter<out Item<out RecyclerView.ViewHolder, out ItemResources>>,
                      viewHolder : ViewHolder,
                      resources : ItemResources?) {
        super.bind(adapter, viewHolder, resources)

        viewHolder.nameTv.text = itemModel.name

        Picasso.with(viewHolder.imageIv.context.applicationContext)
            .load(itemModel.imageUrl)
            .into(viewHolder.imageIv)
    }

    override fun setOnItemClickListener(viewHolder : BaseItem.ViewHolder<*>, onItemClickListener : OnItemClickListener<Header<BaseItem.ViewHolder<*>>>?) {
        viewHolder.itemView.setOnClickListener(ItemClickListener(this, 0, onItemClickListener))
    }

    override fun getLayout() : Int {
        return MAIN_LAYOUT_ID
    }

    class ViewHolder(itemView : View) : BaseItem.ViewHolder<Topic>(itemView) {

        val imageIv = itemView.findViewById<ImageView>(R.id.imageIv)
        val nameTv = itemView.findViewById<TextView>(R.id.nameTv)

    }

    companion object {

        @JvmStatic val MAIN_LAYOUT_ID : Int = R.layout.topic_item_layout

    }

}
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class TopicItem extends BaseItem<Topic, TopicItem.ViewHolder, ItemResources> implements
        Header<BaseItem.ViewHolder> {

    public static final int MAIN_LAYOUT_ID = R.layout.topic_item_layout;

    public TopicItem(Topic itemModel) {
        super(itemModel);
    }

    @Override
    public ViewHolder init(Adapter<? extends Item> adapter,
                           ViewGroup parent,
                           LayoutInflater inflater,
                           ItemResources resources) {
        return new ViewHolder(inflater.inflate(
            MAIN_LAYOUT_ID,
            parent,
            false
        ));
    }

    @Override
    public void bind(Adapter<? extends Item> adapter,
                     ViewHolder viewHolder,
                     ItemResources resources) {
        super.bind(adapter, viewHolder, resources);

        viewHolder.nameTv.setText(getItemModel().getName());

        Picasso.with(viewHolder.imageIv.getContext().getApplicationContext())
            .load(getItemModel().getImageUrl())
            .into(viewHolder.imageIv);
    }

    @Override
    public void setOnItemClickListener(BaseItem.ViewHolder viewHolder, OnItemClickListener<Header<BaseItem.ViewHolder>> onItemClickListener) {
        viewHolder.itemView.setOnClickListener(new ItemClickListener(this, 0, onItemClickListener));
    }

    @Override
    public int getLayout() {
        return MAIN_LAYOUT_ID;
    }

    public static final class ViewHolder extends BaseItem.ViewHolder<Topic> {

        ImageView imageIv;
        TextView nameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            imageIv = itemView.findViewById(R.id.imageIv);
            nameTv = itemView.findViewById(R.id.nameTv);
        }

    }

}
````

</p></details><br>

- Altering the adapters - now we need to enable the Multi-item support (See **Adapter multi-item support** below)

- **Adapter multi-item support**
<br>To enable the support of multiple item types in your adapter you need to change adapter item type to a more general one (e.g. `BaseItem`) (For ListView-based adapters you should also specify the exact View Type Count and the actual Item View Type for a given position).

Here's an example of a multi-item adapter that supports Header and Footer items.
<details><summary><b>Kotlin (click to expand)</b></summary>
<p>
    
````kotlin
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
````

</p></details>

<details><summary><b>Java (click to expand)</b></summary>
<p>
    
````java
public final class SimpleRecyclerViewAdapter extends TrackableRecyclerViewAdapter<Long, BaseItem, BaseItem.ViewHolder> {

    private OnItemClickListener<ArticleItem> onArticleItemClickListener;
    private OnItemClickListener<TopicSuggestionItem> onTopicSuggestionItemClickListener;
    private OnItemLongClickListener<TopicSuggestionItem> onTopicSuggestionItemLongClickListener;
    private OnItemClickListener<FooterItem> onFooterButtonClickListener;

    public SimpleRecyclerViewAdapter(Context context, List<BaseItem> items) {
        super(context, items);
        setHasStableIds(true);
    }

    @Override
    public void assignListeners(BaseItem.ViewHolder holder, int position, BaseItem item) {
        super.assignListeners(holder, position, item);

        switch(item.getLayout()) {

            case ArticleItem.MAIN_LAYOUT_ID:
                ((ArticleItem) item).setOnItemClickListener(((ArticleItem.ViewHolder) holder), onArticleItemClickListener);
                break;

            case FooterItem.MAIN_LAYOUT_ID:
                ((FooterItem) item).setOnButtonClickListener(((FooterItem.ViewHolder) holder), onFooterButtonClickListener);
                break;

            //...

        }
    }

    // Listener setters...

}
````

</p></details><br>

The sample ListView-based multi-item adapter implementation can be found here [`SimpleListViewAdapter.kt`](https://github.com/arthur3486/adapster/blob/master/sample/src/main/java/com/arthurivanets/sample/adapters/SimpleListViewAdapter.kt)

- **More uses**
<br>See the [Sample app](https://github.com/arthur3486/adapster/tree/master/sample/src/main/java/com/arthurivanets/sample).

## Contribution

See the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Hall of Fame

<table>
    <tbody>
        <tr>
            <td valign="middle;">
	            <a href="https://play.google.com/store/apps/details?id=com.arthurivanets.reminder">
                    <img src="https://lh3.googleusercontent.com/uD65OOIxM1-khzDI5OowwdzjD9j8CgelcH9mBGAZWIAsAyoTtEE7smUH9GAf3mCg8AA=s360" width="70" height="70"/>
                </a>
            </td>
            <td valign="middle;"><b>Reminder</b></td>
        </tr>
        <br>
        <tr>
            <td valign="middle;">
                <a href="https://play.google.com/store/apps/details?id=com.arthurivanets.owly">
	                <img src="https://lh3.googleusercontent.com/V1-emL7rlp0UTZfJIN-JPpTev1ZSg2RLjC1WAgKVB0A_1ir32JxTBl8E8zz2KyJlyQ=s360" width="70" height="70"/>
                </a>
            </td>
            <td valign="middle;"><b>Owly</b></td>
        </tr>
        <br>
        <tr>
            <td valign="middle;">
                <a href="https://play.google.com/store/apps/details?id=com.stocksexchange.android">
                    <img src="https://lh3.googleusercontent.com/iYNDVD8yKFGDGKzidmbTLWfa4oWuEhaYyhOElX8rzkzx6pjZks1yWDxnethj3ihkmw=s360" width="70" height="70"/>
                </a>
            </td>
            <td valign="middle;"><b>Stocks Exchange</b></td>
        </tr>
    </tbody>
</table>


> Using Adapster in your app and want it to get listed here? Email me at arthur.ivanets.l@gmail.com!

## License

Adapster is licensed under the [Apache 2.0 License](LICENSE).
