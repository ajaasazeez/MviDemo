package com.example.mymviapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymviapp.R
import com.example.mymviapp.model.NewsModel
import kotlinx.android.synthetic.main.layout_news_item.view.*


class MainListAdapter :
    ListAdapter<NewsModel, MainListAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_news_item, parent, false)
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){


        fun bind(item: NewsModel) = with(itemView) {
            tvTitle.text = item.title

            Glide.with(this)
                .load(item.imageUrl)
                .into(imageView)
        }
    }


    private class NewsDiffCallback : DiffUtil.ItemCallback<NewsModel>() {

        override fun areItemsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}




