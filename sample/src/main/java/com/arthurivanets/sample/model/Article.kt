package com.arthurivanets.sample.model

data class Article(val id : Int,
                   val title : String,
                   val text : String,
                   val imageUrl : String = "") {


    val hasImage : Boolean
        get() = !imageUrl.isBlank()


}