package com.example.ailanguagetranslate.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ailanguagetranslate.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerScreen(
    onNavigate: (String) -> Unit,
) {
    val pageCount = 4
    val pagerState = rememberPagerState { pageCount }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color(0xFFF0F1F8),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) { pageIndex ->

                when (pageIndex) {
                    0 -> OnboardingPageAds(
                        imageRes = R.drawable.image_onboarding,
                        title = "Welcome to the world of \n" + "languages",
                        pageIndex = 0,
                        pageCount = pageCount,
                        onNextClicked = {
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        hasAds = true,
                    )

                    1 -> FullScreenAdPage()

                    2 -> OnboardingPage(
                        imageRes = R.drawable.image_onboarding_two,
                        title = "Be precise with your\n" + " language",
                        pageIndex = 2,
                        pageCount = pageCount,
                        onNextClicked = {
                            scope.launch {
                                pagerState.animateScrollToPage(3)
                            }
                        },
                    )

                    3 -> OnboardingPageAds(
                        imageRes = R.drawable.image_onboarding_three,
                        title = "Without delay\n" +
                            "Anytime, Anywhere",
                        pageIndex = 3,
                        pageCount = pageCount,
                        onNextClicked = {
                        },
                        hasAds = true,
                    )
                }
            }
        }
    }
}
