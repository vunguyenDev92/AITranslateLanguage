package com.example.ailanguagetranslate.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ailanguagetranslate.LanguageApplication.Companion.NATIVE_AD_UNIT_ID
import com.example.ailanguagetranslate.presentation.ad.FullNativeAdLayout
import com.example.ailanguagetranslate.presentation.ad.NativeAdComposable

@Composable
fun FullScreenAdPage() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NativeAdComposable(
            adUnitId = NATIVE_AD_UNIT_ID,
            adLayout = { nativeAd ->
                FullNativeAdLayout(nativeAd = nativeAd)
            },
        )
    }
}
