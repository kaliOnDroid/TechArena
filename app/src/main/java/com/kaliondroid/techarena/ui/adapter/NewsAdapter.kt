package com.kaliondroid.techarena.ui.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaliondroid.techarena.data.models.Article
import com.kaliondroid.techarena.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.TimeZone
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
        private val context: Context = binding.root.context

        fun bind(item: Article) {
            binding.apply {
                item.apply {
                    tvNewsTitle.text = title
                    tvNewsDescription.text = description
                    ivNews.load(context, urlToImage)
                    tvSourceName.text = source?.name ?: "Unknown"

                    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'")
                    sdf.timeZone = TimeZone.getTimeZone("GMT")
                    try {
                        val time = sdf.parse(publishedAt).time
                        val now = System.currentTimeMillis()
                        val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                        tvPostedDate.text = ago
                    } catch (e: Exception) {
                        e.printStackTrace()
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


fun ImageView.load(context: Context, url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}