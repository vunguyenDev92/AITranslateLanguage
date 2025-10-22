package com.example.ailanguagetranslate.presentation.ad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ailanguagetranslate.presentation.util.DismissibleAd
import com.example.ailanguagetranslate.presentation.util.NativeAdBodyView
import com.example.ailanguagetranslate.presentation.util.NativeAdButton
import com.example.ailanguagetranslate.presentation.util.NativeAdCallToActionView
import com.example.ailanguagetranslate.presentation.util.NativeAdHeadlineView
import com.example.ailanguagetranslate.presentation.util.NativeAdIconView
import com.example.ailanguagetranslate.presentation.util.NativeAdMediaView
import com.example.ailanguagetranslate.presentation.util.NativeAdView
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun StandardNativeAdLayout(nativeAd: NativeAd) {
    var showAd by remember { mutableStateOf(true) }

    if (showAd) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp)
                .heightIn(min = 120.dp, max = 261.dp)
                .background(Color(0xFFFAFBFD)),
        ) {
            NativeAdView(nativeAd) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    DismissibleAd(nativeAd, onDismiss = { showAd = false })
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, start = 16.dp, end = 8.dp),
                    ) {
                        nativeAd.icon?.let { icon ->
                            NativeAdIconView(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(end = 4.dp),
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
                                .height(120.dp),
                        )
                    }

                    nativeAd.callToAction?.let { callToAction ->
                        NativeAdCallToActionView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, end = 12.dp, start = 8.dp),
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
}

@Composable
fun SmallNativeAdLayout(nativeAd: NativeAd) {
    var showAd by remember { mutableStateOf(true) }
    if (showAd) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFFFAFBFD)),
        ) {
            NativeAdView(nativeAd) {
                Column(
                    Modifier
                        .fillMaxWidth(),
                ) {
                    DismissibleAd(nativeAd, onDismiss = { showAd = false })
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                    ) {
                        nativeAd.mediaContent?.let {
                            NativeAdMediaView(
                                modifier = Modifier
                                    .weight(0.6f)
                                    .aspectRatio(16f / 9f)
                                    .padding(end = 10.dp),
                            )
                        }

                        Column(
                            modifier = Modifier.weight(0.45f),
                        ) {
                            nativeAd.icon?.let { icon ->
                                NativeAdIconView(Modifier.size(40.dp)) {
                                    AsyncImage(
                                        icon.drawable ?: icon.uri,
                                        "Ad Icon",
                                        Modifier.fillMaxSize(),
                                    )
                                }
                            }
                            nativeAd.headline?.let { headline ->
                                NativeAdHeadlineView(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                    Text(
                                        text = headline,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        lineHeight = 18.sp,
                                    )
                                }
                            }
                            nativeAd.body?.let { body ->
                                NativeAdBodyView(Modifier.fillMaxWidth().padding(top = 4.dp)) {
                                    Text(
                                        text = body,
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.Gray,
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                }
                            }
                        }
                    }
                    nativeAd.callToAction?.let { callToAction ->
                        NativeAdCallToActionView(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, start = 8.dp, end = 8.dp),
                        ) {
                            NativeAdButton(
                                text = callToAction,
                                Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FullNativeAdLayout(nativeAd: NativeAd) {
    var showAd by remember { mutableStateOf(true) }
    if (showAd) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F1F8)),
        ) {
            DismissibleAd(nativeAd, onDismiss = { showAd = false })

            NativeAdView(
                nativeAd = nativeAd,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    nativeAd.mediaContent?.let {
                        NativeAdMediaView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .align(Alignment.Center)
                                .padding(horizontal = 16.dp),
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFFAFBFD))
                                .padding(bottom = 8.dp),
                        ) {
                            DismissibleAd(nativeAd, onDismiss = { showAd = false })
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, bottom = 8.dp),
                            ) {
                                nativeAd.icon?.let { icon ->
                                    NativeAdIconView(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .padding(end = 4.dp),
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
                                                maxLines = 2,
                                                overflow = TextOverflow.Ellipsis,
                                            )
                                        }
                                    }
                                    nativeAd.body?.let {
                                        NativeAdBodyView(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 4.dp),
                                        ) {
                                            Text(
                                                text = it,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = Color.Gray,
                                                maxLines = 3,
                                                overflow = TextOverflow.Ellipsis,
                                            )
                                        }
                                    }
                                }
                            }

                            nativeAd.callToAction?.let { callToAction ->
                                NativeAdCallToActionView(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, start = 8.dp, end = 8.dp),
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
        }
    }
}
