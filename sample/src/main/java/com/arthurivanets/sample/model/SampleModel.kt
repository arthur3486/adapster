package com.arthurivanets.sample.model

import java.io.Serializable

/**
 * Created by arthur3486
 */
data class SampleModel(val id : Long = -1,
                       val title : String = "",
                       val fullText : String = "") : Serializable {

}