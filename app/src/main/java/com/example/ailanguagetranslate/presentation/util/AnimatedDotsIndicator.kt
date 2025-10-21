package com.example.ailanguagetranslate.presentation.util // Hoặc package bạn muốn

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedDotsIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFF3B5AFB),
    inactiveColor: Color = Color(0xFFD6DFFF),
    dotSize: Dp = 8.dp,
    activeDotWidth: Dp = 24.dp,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        repeat(pageCount) { index ->
            val isSelected = (index == currentPage)

            val width by animateDpAsState(
                targetValue = if (isSelected) activeDotWidth else dotSize,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing,
                ),
                label = "DotWidthAnimation",
            )

            val color by animateColorAsState(
                targetValue = if (isSelected) activeColor else inactiveColor,
                animationSpec = tween(durationMillis = 300),
                label = "DotColorAnimation",
            )

            Box(
                modifier = Modifier
                    .height(dotSize)
                    .width(width)
                    .clip(CircleShape)
                    .background(color),
            )
        }
    }
}
