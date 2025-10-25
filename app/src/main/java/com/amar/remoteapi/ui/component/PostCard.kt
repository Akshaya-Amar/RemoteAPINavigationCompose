package com.amar.remoteapi.ui.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amar.remoteapi.data.model.Post

@Composable
fun PostCard(
      post: Post,
      onPostClick: (Post) -> Unit
) {
      Card(
            modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 8.dp, horizontal = 16.dp)
                  .clickable { onPostClick(post) },
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
            Column(modifier = Modifier.padding(16.dp)) {
                  Text(
                        text = post.title,
                        style = MaterialTheme.typography.titleMedium
                  )

                  Text(text = post.body)
            }
      }
}