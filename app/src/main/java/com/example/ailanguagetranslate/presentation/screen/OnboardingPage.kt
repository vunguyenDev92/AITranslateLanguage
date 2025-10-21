package com.example.ailanguagetranslate.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ailanguagetranslate.LanguageApplication.Companion.NATIVE_AD_UNIT_ID
import com.example.ailanguagetranslate.presentation.ad.NativeAdComposable
import com.example.ailanguagetranslate.presentation.ad.SmallNativeAdLayout
import com.example.ailanguagetranslate.presentation.util.AnimatedDotsIndicator

@Composable
fun OnboardingPage(
    imageRes: Int,
    title: String,
    pageIndex: Int,
    pageCount: Int,
    onNextClicked: () -> Unit,
    hasAds: Boolean = false,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(410.dp),
            )
        }

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                color = Color(0xFF111213),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            TextButton(onClick = onNextClicked) {
                Text(
                    text = "Next",
                    color = Color(0xFF3D4CFB),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(Modifier.height(8.dp))
            AnimatedDotsIndicator(
                pageCount = pageCount,
                currentPage = pageIndex,
            )
            Spacer(Modifier.height(41.dp))
            if (hasAds) {
                NativeAdComposable(
                    adUnitId = NATIVE_AD_UNIT_ID,
                    adLayout = { nativeAd ->
                        SmallNativeAdLayout(nativeAd = nativeAd)
                    },
                )
            }
        }
    }
}
