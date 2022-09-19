package com.app.zuludin.newsstand.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.zuludin.newsstand.R
import com.app.zuludin.newsstand.core.theme.Shapes
import com.app.zuludin.newsstand.domain.model.News

@Composable
fun NewsItemHorizontal(
    modifier: Modifier = Modifier,
    news: News
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        shape = Shapes.medium.copy(CornerSize(10.dp)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .width(320.dp)
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(news.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Surface(
                    shape = CircleShape,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = news.source,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = news.title,
                style = MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = CircleShape, color = Color.Gray) {
                    Text(
                        modifier = Modifier.padding(6.dp),
                        text = "HR",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = news.author, style = MaterialTheme.typography.subtitle2)
                    Text(text = news.publish, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun NewsItemVertical(
    modifier: Modifier = Modifier,
    news: News,
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.surface,
        shape = Shapes.medium.copy(CornerSize(10.dp)),
        modifier = modifier
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(news.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = news.source, color = Color.Gray, fontSize = 13.sp)
                    Text(text = news.publish, color = Color.Gray, fontSize = 13.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = news.title,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(shape = CircleShape, color = Color.Gray) {
                        Text(
                            modifier = Modifier.padding(6.dp),
                            text = "HR",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = news.author, style = MaterialTheme.typography.subtitle2)
                }
            }
        }
    }
}
