package com.amar.remoteapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.amar.remoteapi.ui.screen.PostDetailScreen
import com.amar.remoteapi.ui.screen.PostScreen
import com.amar.remoteapi.ui.screen.Screen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
      val backStack = rememberNavBackStack(Screen.PostList)

      NavDisplay(
            modifier = modifier,
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                  when (key) {
                        is Screen.PostList -> NavEntry(key) {
                              PostScreen(
                                    onPostClicked = { post ->
                                          backStack.add(Screen.PostDetail(post))
                                    }
                              )
                        }

                        is Screen.PostDetail -> NavEntry(key) {
                              PostDetailScreen(post = key.post)
                        }

                        else -> throw RuntimeException("No screen found")
                  }
            }
      )
}