package com.example.ailanguagetranslate.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ailanguagetranslate.LanguageApplication.Companion.NATIVE_AD_UNIT_ID
import com.example.ailanguagetranslate.presentation.ad.NativeAdComposable
import com.example.ailanguagetranslate.presentation.ad.StandardNativeAdLayout

@Composable
fun FullScreenAdPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NativeAdComposable(
            adUnitId = NATIVE_AD_UNIT_ID,
            adLayout = { nativeAd ->
                StandardNativeAdLayout(nativeAd = nativeAd)
            },
        )
    }
}
