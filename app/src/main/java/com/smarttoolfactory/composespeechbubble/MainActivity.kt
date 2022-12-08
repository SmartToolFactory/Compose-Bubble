@file:OptIn(ExperimentalFoundationApi::class)
package com.smarttoolfactory.composespeechbubble

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.composespeechbubble.demo.DemoBubble
import com.smarttoolfactory.composespeechbubble.demo.DemoDynamicSize
import com.smarttoolfactory.composespeechbubble.demo.DemoFullChat
import com.smarttoolfactory.composespeechbubble.demo.DemoSimpleLayout
import com.smarttoolfactory.composespeechbubble.ui.theme.ComposeSpeechBubbleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSpeechBubbleTheme {
                // A surface container using the 'background' color from the theme
                Surface{

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        HomeContent()
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeContent() {

    val pagerState: PagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    ScrollableTabRow(
        containerColor = Color(0xff00897B),
        contentColor = Color.White,
        edgePadding = 8.dp,
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
    ) {
        // Add tabs for all of our pages
        tabList.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        pageCount = tabList.size,
        beyondBoundsPageCount = 3
    ) { page: Int ->

        when (page) {
            0 ->  DemoFullChat()
            1 -> DemoDynamicSize()
            2 ->  DemoBubble()
            else -> DemoSimpleLayout()
        }
    }
}

internal val tabList = listOf("Chat", "Dynamic Size", "Bubbles", "Simple Layout")
