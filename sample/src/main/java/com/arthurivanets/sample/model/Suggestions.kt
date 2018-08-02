package com.arthurivanets.sample.model

data class Suggestions<ModelType>(val id : Int,
                                  val title : String = "",
                                  val suggestedItems : MutableList<ModelType>) {

}