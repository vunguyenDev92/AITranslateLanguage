package com.example.ailanguagetranslate.presentation.ad

import android.content.Context
import android.util.Log
import com.example.ailanguagetranslate.LanguageApplication.Companion.NATIVE_AD_UNIT_ID
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd

object NativeAdManager {
    private var nativeAd: NativeAd? = null
    private var isLoading = false

    fun preloadAd(context: Context) {
        if (isLoading || nativeAd != null) {
            return
        }

        isLoading = true
        val adLoader = AdLoader.Builder(context, NATIVE_AD_UNIT_ID)
            .forNativeAd { ad ->
                nativeAd = ad
                isLoading = false
                Log.d("NativeAdManager", "Native ad preloaded successfully")
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(error: LoadAdError) {
                    Log.e("NativeAdManager", "Native ad failed to preload: ${error.message}")
                    isLoading = false
                }

                override fun onAdLoaded() {
                    Log.d("NativeAdManager", "Native ad loaded")
                }
            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    fun getAd(): NativeAd? {
        return nativeAd
    }

    fun destroyAd() {
        nativeAd?.destroy()
        nativeAd = null
    }

    fun reloadAd(context: Context) {
        destroyAd()
        isLoading = false
        preloadAd(context)
    }
}
