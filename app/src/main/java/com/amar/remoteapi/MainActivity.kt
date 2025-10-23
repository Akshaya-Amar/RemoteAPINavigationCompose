package com.amar.remoteapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amar.remoteapi.ui.theme.RemoteAPITheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  RemoteAPITheme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                              Greeting(
                                    name = "Android",
                                    modifier = Modifier.padding(innerPadding)
                              )
                        }
                  }
            }
      }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
      var posts by remember { mutableStateOf<List<Post>>(emptyList()) }

      LaunchedEffect(Unit) {
            val client = HttpClient(CIO) {
                  install(ContentNegotiation) {
                        json(Json {
                              ignoreUnknownKeys = true
                        })
                  }
            }
            try {
                  val response = client.get {
                        url("https://jsonplaceholder.typicode.com/posts")
                  }
                  posts = response.body<List<Post>>()
                  Log.d("check...", "Greeting: $posts")
            } catch (exception: Exception) {
                  Log.d("check...", "Exception: -> ${exception.message}")
            }
      }

      LazyColumn(modifier = modifier) {
            items(posts) { post ->
                  Card(
                        modifier = Modifier
                              .fillMaxWidth()
                              .padding(16.dp)
                  ) {
                        Column(
                              modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                        ) {
                              Text(
                                    text = post.title,
                                    style = MaterialTheme.typography.titleLarge
                              )
                              Text(text = post.body)
                        }
                  }
            }
      }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
      RemoteAPITheme {
            Greeting("Android")
      }
}