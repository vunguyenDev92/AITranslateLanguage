package com.example.ailanguagetranslate.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdBadge(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .size(16.dp)
            .background(Color(0xFFD2E8FF), shape = RectangleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Ad",
            color = Color(0xFF2E8AE5),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier,
        )
    }
}
