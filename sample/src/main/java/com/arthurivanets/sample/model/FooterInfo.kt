package com.arthurivanets.sample.model

class FooterInfo(val id : Int,
                 val message : String,
                 val buttonTitle : String,
                 val imageUrl : String = "") {


    val hasImage : Boolean
        get() = !imageUrl.isBlank()


}