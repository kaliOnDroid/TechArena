package com.kaliondroid.techarena.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaliondroid.techarena.data.models.NewsItem
import com.kaliondroid.techarena.databinding.ItemNewsBinding
import javax.inject.Inject

class NewsAdapter @Inject constructor() :
    PagingDataAdapter<NewsItem, NewsAdapter.NewsViewHolder>(NewsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsItem) {
            binding.apply {
                item.apply {
                    tvNewsTitle.text = title
                    tvNewsDescription.text = description
                    Glide.with(binding.root.context)
                        .load(image)
                        .into(ivNews)
                    tvSourceName.text = source
                }
            }
        }
    }

    object NewsComparator : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }
    }

}