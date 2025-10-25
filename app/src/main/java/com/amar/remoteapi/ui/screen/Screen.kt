package com.amar.remoteapi.ui.screen

import androidx.navigation3.runtime.NavKey
import com.amar.remoteapi.data.model.Post
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
      @Serializable object PostList : Screen
      @Serializable data class PostDetail(val post: Post) : Screen
}