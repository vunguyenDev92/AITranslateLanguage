package com.example.ailanguagetranslate

import android.app.Application
import com.google.android.gms.ads.MobileAds

class LanguageApplication : Application() {
    companion object {
        const val TAG = "GoogleMobileAdsSample"

        const val BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"
        const val NATIVE_AD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
        const val INTER_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    }

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        container = AppContainer()
    }
}
