package com.arthurivanets.sample.model

data class Topic(val id : Int,
                 val name : String,
                 val imageUrl : String = "") {


    val hasImage : Boolean
        get() = !imageUrl.isBlank()


}