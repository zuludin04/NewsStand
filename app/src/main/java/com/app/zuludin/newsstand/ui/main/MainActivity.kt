package com.app.zuludin.newsstand.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.newsstand.R
import com.app.zuludin.newsstand.data.Resource
import com.app.zuludin.newsstand.domain.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.recycler_news)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        viewModel.news.observe(this) { news ->
            if (news != null) {
                when (news) {
                    is Resource.Error -> progressBar.visibility = View.GONE
                    is Resource.Loading -> progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        setupGameRecyclerView(rv, news.data)
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setupGameRecyclerView(rv: RecyclerView, news: List<News>?) {
        val newsAdapter = NewsAdapter()
        newsAdapter.addNews(news)

        with(rv) {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }
}