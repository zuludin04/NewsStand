package com.app.zuludin.newsstand.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.newsstand.R
import com.app.zuludin.newsstand.domain.model.News
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    private val newsList = ArrayList<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    fun addNews(news: List<News>?) {
        if (news == null) return
        newsList.clear()
        newsList.addAll(news)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(news: News) {
        val image = view.findViewById<ImageView>(R.id.news_image)
        val title = view.findViewById<TextView>(R.id.news_title)

        title.text = news.title
        Glide.with(view.context).load(news.image).into(image)
    }
}