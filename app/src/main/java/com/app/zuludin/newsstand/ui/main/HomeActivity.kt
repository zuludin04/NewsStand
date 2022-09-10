package com.app.zuludin.newsstand.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.zuludin.newsstand.core.theme.NewsStandTheme
import com.app.zuludin.newsstand.ui.main.components.CategoryItem
import com.app.zuludin.newsstand.ui.main.components.NewsItemHorizontal
import com.app.zuludin.newsstand.ui.main.components.NewsItemVertical
import com.app.zuludin.newsstand.ui.main.components.SectionHeader

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStandTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xfff8f8f8)
                ) {
                    HomeNewsLayout()
                }
            }
        }
    }
}

@Composable
fun HomeNewsLayout(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Hottest News")
                Spacer(modifier = Modifier.height(16.dp))
                HottestNews()
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Explore")
                Spacer(modifier = Modifier.height(16.dp))
                ExploreCategory()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        items(10) {
            NewsItemVertical(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun HottestNews() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(10) {
            NewsItemHorizontal()
        }
    }
}

@Composable
fun ExploreCategory() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(5) {
            CategoryItem()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xfff5f5f5)
@Composable
fun PreviewHomeLayout() {
    NewsStandTheme {
        HomeNewsLayout()
    }
}