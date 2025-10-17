package com.example.ailanguagetranslate.presentation.ad

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
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
        // Load the native ad when we launch this screen
        loadNativeAd(
            context = context,
            onAdLoaded = { ad ->
                // Handle the native ad being loaded.
                if (!isDisposed) {
                    nativeAd = ad
                } else {
                    // Destroy the native ad if loaded after the screen is disposed.
                    ad.destroy()
                }
            },
        )
        // Destroy the native ad to prevent memory leaks when we dispose of this screen.
        onDispose {
            isDisposed = true
            nativeAd?.destroy()
            nativeAd = null
        }
    }

    // Display the native ad view with a user defined template.
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
            .height(260.dp)
            .background(Color(0xFFFAFBFD)),
    ) {
        NativeAdView(nativeAd) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    nativeAd.icon?.let { icon ->
                        NativeAdIconView(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp),
                        ) {
                            icon.drawable?.toBitmap()?.let { bitmap ->
                                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Ad Icon")
                            }
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        nativeAd.headline?.let {
                            NativeAdHeadlineView {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black,
                                )
                            }
                        }

                        nativeAd.body?.let {
                            NativeAdBodyView {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                    maxLines = 1,
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    NativeAdMediaView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .heightIn(min = 120.dp, max = 200.dp),
                    )

                    nativeAd.callToAction?.let { callToAction ->
                        NativeAdCallToActionView(
                            modifier = Modifier
                                .height(40.dp)
                                .weight(0.6f),
                        ) {
                            NativeAdButton(
                                text = callToAction,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

// [END display_native_ad]
