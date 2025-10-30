package com.example.ailanguagetranslate.presentation.ad

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.ailanguagetranslate.LanguageApplication.Companion.TAG
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun NativeAdComposable(
    adUnitId: String = "",
    adLayout: @Composable (NativeAd) -> Unit,
) {
    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }
    val context = LocalContext.current
    var isDisposed by remember { mutableStateOf(false) }

    DisposableEffect(adUnitId) {
        val preloadedAd = NativeAdManager.getAd()
        if (preloadedAd != null) {
            if (!isDisposed) {
                nativeAd = preloadedAd
                Log.d(TAG, "Using preloaded native ad")
            }
        } else {
            loadNativeAd(
                context = context,
                adUnitId = adUnitId,
                onAdLoaded = { ad ->
                    if (!isDisposed) {
                        nativeAd = ad
                    } else {
                        ad.destroy()
                    }
                },
            )
        }

        onDispose {
            isDisposed = true
        }
    }

    nativeAd?.let { adValue ->
        adLayout(adValue)
    }
}

fun loadNativeAd(
    context: Context,
    adUnitId: String,
    onAdLoaded: (NativeAd) -> Unit,
) {
    val adLoader =
        AdLoader.Builder(context, adUnitId)
            .forNativeAd { nativeAd -> onAdLoaded(nativeAd) }
            .withAdListener(
                object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e(TAG, "Native ad failed to load: ${error.message} (ID: $adUnitId)")
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
