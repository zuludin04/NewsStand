package com.app.zuludin.newsstand.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.zuludin.newsstand.core.newsCategories
import com.app.zuludin.newsstand.core.theme.NewsStandTheme
import com.app.zuludin.newsstand.domain.model.News
import com.app.zuludin.newsstand.ui.main.components.CategoryItem
import com.app.zuludin.newsstand.ui.main.components.NewsItemHorizontal
import com.app.zuludin.newsstand.ui.main.components.SectionHeader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStandTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xfff8f8f8)
                ) {
                    HomeRoute(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun HomeRoute(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val homeListLazyListState = rememberLazyListState()

    HomeNewsLayout(uiState = uiState, homeListLazyListState = homeListLazyListState)
}

@Composable
fun HomeNewsLayout(
    uiState: MainNewsUiState,
    homeListLazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    HomeScreenWithList(
        uiState = uiState,
        modifier = modifier
    ) { hasPostsUiState, contentModifier ->
        NewsList(
            news = hasPostsUiState.news,
            modifier = contentModifier,
            state = homeListLazyListState
        )
    }
//    LazyColumn(modifier = modifier) {
//        item {
//            Column {
//                Spacer(modifier = Modifier.height(16.dp))
//                SectionHeader(title = "Hottest News")
//                Spacer(modifier = Modifier.height(16.dp))
//                HottestNews()
//                Spacer(modifier = Modifier.height(16.dp))
//                SectionHeader(title = "Explore")
//                Spacer(modifier = Modifier.height(16.dp))
//                ExploreCategory()
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }
//        items(10) {
//            NewsItemVertical(modifier = Modifier.padding(8.dp))
//        }
//    }
}

@Composable
fun NewsList(
    news: List<News>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Hottest News")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item {
            HottestNews(news)
        }
        item {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Explore")
                Spacer(modifier = Modifier.height(16.dp))
                ExploreCategory()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HomeScreenWithList(
    uiState: MainNewsUiState,
    modifier: Modifier = Modifier,
    hasNewsContent: @Composable (
        uiState: MainNewsUiState.HasNews,
        contentModifier: Modifier
    ) -> Unit
) {
    Scaffold { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        LoadingContent(
            empty = when (uiState) {
                is MainNewsUiState.HasNews -> false
                is MainNewsUiState.NoNews -> uiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
            content = {
                when (uiState) {
                    is MainNewsUiState.HasNews -> hasNewsContent(uiState, contentModifier)
                    is MainNewsUiState.NoNews -> {
                        if (uiState.errorMessages.isEmpty()) {
                            TextButton(
                                onClick = {},
                                modifier.fillMaxSize()
                            ) {
                                Text(
                                    "Tap To Load News",
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Box(contentModifier.fillMaxSize()) { /* empty screen */ }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        content()
    }
}

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun HottestNews(news: List<News>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(news) {
            NewsItemHorizontal(news = it)
        }
    }
}

@Composable
fun ExploreCategory() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(newsCategories) {
            CategoryItem(category = it)
        }
    }
}