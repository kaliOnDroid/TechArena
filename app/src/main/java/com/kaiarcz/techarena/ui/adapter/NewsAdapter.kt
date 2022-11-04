package com.kaiarcz.techarena.ui.adapter

import android.content.Context
import android.content.Intent
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaiarcz.techarena.data.models.Article
import com.kaiarcz.techarena.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


class NewsAdapter(
    private val onItemClicked: (String?) -> Unit
) : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(NewsComparator) {

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
                    ivNews.load(urlToImage)
                    source?.name?.let { name ->
                        tvSourceName.text = name
                        ivSrc.avatarInitials = sourceInitials(name)
                    }
                    tvPostedDate.text = timeAgo(publishedAt)
                    tvShareCta.setOnClickListener {
                        share(context, url)
                    }
                    tvTapToReadCta.setOnClickListener {
                        onItemClicked(url)
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

    fun sourceInitials(name: String): String {
        return name.split(' ').map {
            it[0]
        }.joinToString()
    }

    fun share(context: Context, url: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun timeAgo(publishedDate: String?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val time = sdf.parse(publishedDate).time
            val now = System.currentTimeMillis()
            val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            return ago.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}
