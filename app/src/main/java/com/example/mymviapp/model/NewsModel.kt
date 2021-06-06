package com.example.mymviapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsModel (
    @Expose
    @SerializedName("id")
    val id: String? = null,
    @Expose
    @SerializedName("title")
    val title: String? = null,
    @Expose
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @Expose
    @SerializedName("newsSite")
    val newsSite: String? = null
){
    override fun toString(): String {
        return "NewsModel(title=$title, url=$imageUrl, newsSite=$newsSite)"
    }
}