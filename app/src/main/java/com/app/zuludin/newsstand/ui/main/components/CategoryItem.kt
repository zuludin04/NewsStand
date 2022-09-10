package com.app.zuludin.newsstand.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.zuludin.newsstand.R

@Composable
fun CategoryItem(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.poster),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(88.dp)
        )
        Surface(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            color = Color(0x80000000),
        ) {}
        Text(
            text = "Category",
            textAlign = TextAlign.Center,
            color = Color.White,
            letterSpacing = 1.5.sp
        )
    }
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem()
}