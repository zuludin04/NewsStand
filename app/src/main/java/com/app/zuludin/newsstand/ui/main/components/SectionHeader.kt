package com.app.zuludin.newsstand.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SectionHeader(modifier: Modifier = Modifier, title: String) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = title, style = MaterialTheme.typography.h6)
        Text(
            text = "See More",
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
fun PreviewSectionHeader() {
    SectionHeader(title = "Hottest News")
}