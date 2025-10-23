package com.amar.remoteapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amar.remoteapi.ui.theme.RemoteAPITheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.url

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
      LaunchedEffect(Unit) {
            val client = HttpClient(CIO)
            try {
                  val response = client.get {
                        url("https://jsonplaceholder.typicode.com/posts")
                  }
                  val body = response.body<String>()
                  Log.d("check...", "Greeting: $body")
            } catch (exception: Exception) {
                  Log.d("check...", "Exception: -> ${exception.message}")
            }
      }
      Text(
            text = "Hello $name!",
            modifier = modifier
      )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
      RemoteAPITheme {
            Greeting("Android")
      }
}