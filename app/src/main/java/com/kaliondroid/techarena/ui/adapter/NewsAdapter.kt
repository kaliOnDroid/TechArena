package com.kaliondroid.techarena.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaliondroid.techarena.data.models.Article
import com.kaliondroid.techarena.databinding.ItemNewsBinding
import javax.inject.Inject

class NewsAdapter @Inject constructor() :
    PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(NewsComparator) {

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
        private val context = binding.root.context
        fun bind(item: Article) {
            binding.apply {
                item.apply {
                    tvNewsTitle.text = title
                    tvNewsDescription.text = description
                    Glide.with(binding.root.context)
                        .load(urlToImage)
                        .into(ivNews)
                    tvSourceName.text = source?.name ?: "Unknown"
                    ivShare.setOnClickListener {
                        val shareIntent = Intent.createChooser(
                            Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, url)
                                putExtra(Intent.EXTRA_TEXT, title)
                                type = "text/plain"
                            },
                            null
                        )
                        context.startActivity(shareIntent)
                    }
                }
            }
        }
    }

    object NewsComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

}