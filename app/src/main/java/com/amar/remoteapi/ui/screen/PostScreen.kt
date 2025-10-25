package com.amar.remoteapi.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amar.remoteapi.common.network.ApiResult
import com.amar.remoteapi.data.model.Post
import com.amar.remoteapi.ui.component.ErrorView
import com.amar.remoteapi.ui.component.LoadingView
import com.amar.remoteapi.ui.component.PostList
import com.amar.remoteapi.ui.viewmodel.PostViewModel

@Composable
fun PostScreen(
      modifier: Modifier = Modifier,
      viewModel: PostViewModel = hiltViewModel(),
      onPostClicked: (Post) -> Unit
) {
      val postResult by viewModel.posts.collectAsStateWithLifecycle()
      when (postResult) {
            is ApiResult.Loading -> {
                  Log.d("check...", "PostScreen: Loading")
                  LoadingView()
            }

            is ApiResult.Failure -> {
                  val message = (postResult as ApiResult.Failure).message
                  Log.d("check...", "PostScreen: $message")
                  ErrorView(message = message, onRetry = { viewModel.getPosts() })
            }

            is ApiResult.Success -> {
                  val posts = (postResult as ApiResult.Success<List<Post>>).data
                  Log.d("check...", "PostScreen: $posts")
                  PostList(
                        modifier = modifier,
                        posts = posts,
                        onPostClick = onPostClicked
                  )
            }
      }
}
