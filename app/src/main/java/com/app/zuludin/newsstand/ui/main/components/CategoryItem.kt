package com.app.zuludin.newsstand.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.zuludin.newsstand.domain.model.Category

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = category.image),
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
                color = Color(0x50000000),
            ) {}
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.name,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 13.sp,
            letterSpacing = 1.5.sp
        )
    }
}
