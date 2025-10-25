package com.amar.remoteapi.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.amar.remoteapi.data.model.Post

@Composable
fun PostDetailScreen(
      post: Post
) {
      Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
      ) {
            Card(
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                        .align(Alignment.Center)
            ) {
                  Column(
                        modifier = Modifier.padding(32.dp)
                  ) {
                        Text(
                              text = post.title,
                              style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = post.body)
                  }
            }
      }
}