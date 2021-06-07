package com.example.mymviapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity
data class NewsModel (
    @Expose
    @SerializedName("id")
    @PrimaryKey val id: String,
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