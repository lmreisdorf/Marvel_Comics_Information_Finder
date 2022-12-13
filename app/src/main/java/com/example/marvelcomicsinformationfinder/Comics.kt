package com.example.marvelcomicsinformationfinder

import com.google.gson.annotations.SerializedName

class Comics {
    @JvmField
    @SerializedName("title")
    var title: String? = null

    @JvmField
    @SerializedName("thumbnail")
    var picture: Thumbnail? = null

    @JvmField
    @SerializedName("description")
    var description: String? = null
}

/**
 *         "thumbnail": {
"path": "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
"extension": "jpg"
},
 */
class Thumbnail {
    //path, extension, toString()
    val path: String? =null
    val extension: String? =null

    override fun toString(): String {
        val https = path?.replace("http:","https:")
        return "$https.$extension"
    }

}