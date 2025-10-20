package com.example.ailanguagetranslate.presentation.ad

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ailanguagetranslate.LanguageApplication.Companion.NATIVE_AD_UNIT_ID
import com.example.ailanguagetranslate.LanguageApplication.Companion.TAG
import com.example.ailanguagetranslate.presentation.util.NativeAdBodyView
import com.example.ailanguagetranslate.presentation.util.NativeAdButton
import com.example.ailanguagetranslate.presentation.util.NativeAdCallToActionView
import com.example.ailanguagetranslate.presentation.util.NativeAdHeadlineView
import com.example.ailanguagetranslate.presentation.util.NativeAdIconView
import com.example.ailanguagetranslate.presentation.util.NativeAdMediaView
import com.example.ailanguagetranslate.presentation.util.NativeAdView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd

// [START ad_listener]
/** Load and display a native ad. */

@Composable
fun NativeAdComposable() {
    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }
    val context = LocalContext.current
    var isDisposed by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        loadNativeAd(
            context = context,
            onAdLoaded = { ad ->
                if (!isDisposed) {
                    nativeAd = ad
                } else {
                    ad.destroy()
                }
            },
        )
        onDispose {
            isDisposed = true
            nativeAd?.destroy()
            nativeAd = null
        }
    }

    nativeAd?.let { adValue -> DisplayNativeAdView(adValue) }
}

fun loadNativeAd(context: Context, onAdLoaded: (NativeAd) -> Unit) {
    val adLoader =
        AdLoader.Builder(context, NATIVE_AD_UNIT_ID)
            .forNativeAd { nativeAd -> onAdLoaded(nativeAd) }
            .withAdListener(
                object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e(TAG, "Native ad failed to load: ${error.message}")
                    }

                    override fun onAdLoaded() {
                        Log.d(TAG, "Native ad was loaded.")
                    }

                    override fun onAdImpression() {
                        Log.d(TAG, "Native ad recorded an impression.")
                    }

                    override fun onAdClicked() {
                        Log.d(TAG, "Native ad was clicked.")
                    }
                },
            )
            .build()
    adLoader.loadAd(AdRequest.Builder().build())
}

// [END ad_listener]

// [START display_native_ad]

/** Display a native ad with a user defined template. */
@Composable
fun DisplayNativeAdView(nativeAd: NativeAd) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .heightIn(min = 120.dp, max = 350.dp)
            .background(Color(0xFFFAFBFD))
            .padding(8.dp),
    ) {
        NativeAdView(nativeAd) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                ) {
                    nativeAd.icon?.let { icon ->
                        NativeAdIconView(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(end = 12.dp),
                        ) {
                            AsyncImage(
                                model = icon.drawable ?: icon.uri,
                                contentDescription = "Ad Icon",
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        nativeAd.headline?.let {
                            NativeAdHeadlineView(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                        nativeAd.body?.let {
                            NativeAdBodyView(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }

                if (nativeAd.mediaContent != null) {
                    NativeAdMediaView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f),
                    )
                }

                nativeAd.callToAction?.let { callToAction ->
                    NativeAdCallToActionView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        NativeAdButton(
                            text = callToAction,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }
}

// [END display_native_ad]
