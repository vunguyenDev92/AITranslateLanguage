package com.example.ailanguagetranslate.presentation.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun DismissibleAd(
    nativeAd: NativeAd,
    enableDismiss: Boolean = true,
    onDismiss: (() -> Unit)? = null,
) {
    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        exit = fadeOut(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AdBadge(
                modifier = Modifier
                    .padding(start = 0.dp, top = 0.dp),
            )
            Box {
                Row {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color(0xFFD2E8FF), shape = RectangleShape)
                            .clickable {},
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Color(0xFF2E8AE5),
                            modifier = Modifier
                                .size(16.dp),
                        )
                    }
                    if (enableDismiss) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 2.dp)
                                .background(Color(0xFFD2E8FF), shape = RectangleShape)
                                .clickable {
                                    nativeAd.destroy()
                                    visible = false
                                    onDismiss?.invoke()
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color(0xFF2E8AE5),
                                modifier = Modifier
                                    .padding(end = 0.dp)
                                    .size(16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
