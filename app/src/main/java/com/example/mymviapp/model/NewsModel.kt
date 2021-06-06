package com.example.mymviapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsModel (
    @Expose
    @SerializedName("title")
    val title: String? = null,
    @Expose
    @SerializedName("url")
    val url: String? = null,
    @Expose
    @SerializedName("newsSite")
    val newsSite: String? = null
){
    override fun toString(): String {
        return "NewsModel(title=$title, url=$url, newsSite=$newsSite)"
    }
}