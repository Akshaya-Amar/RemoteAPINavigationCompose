package com.amar.remoteapi.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amar.remoteapi.common.ApiResult
import com.amar.remoteapi.data.model.Post
import com.amar.remoteapi.ui.component.ErrorState
import com.amar.remoteapi.ui.component.LoadingState
import com.amar.remoteapi.ui.component.PostList
import com.amar.remoteapi.ui.viewmodel.PostViewModel

@Composable
fun PostScreen(
      modifier: Modifier = Modifier,
      viewModel: PostViewModel = hiltViewModel()
) {
      val postResult by viewModel.posts.collectAsStateWithLifecycle()
      when (postResult) {
            is ApiResult.Loading -> {
                  Log.d("check...", "PostScreen: Loading")
                  LoadingState()
            }

            is ApiResult.Failure -> {
                  Log.d("check...", "PostScreen: ${(postResult as ApiResult.Failure).message}")
                  ErrorState()
            }

            is ApiResult.Success -> {
                  val posts = (postResult as ApiResult.Success<List<Post>>).data
                  Log.d("check...", "PostScreen: $posts")
                  PostList(modifier, posts)
            }
      }
}
