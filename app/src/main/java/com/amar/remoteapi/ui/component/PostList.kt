package com.amar.remoteapi.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amar.remoteapi.data.model.Post

@Composable
fun PostList(
      modifier: Modifier,
      posts: List<Post>,
      onPostClick: (Post) -> Unit
) {
      LazyColumn(modifier = modifier) {
            items(posts) { post ->
                  PostCard(
                        post = post,
                        onPostClick = { onPostClick(post) }
                  )
            }
      }
}