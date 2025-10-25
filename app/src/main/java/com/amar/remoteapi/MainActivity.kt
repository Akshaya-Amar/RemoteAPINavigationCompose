package com.amar.remoteapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.amar.remoteapi.ui.navigation.AppNavigation
import com.amar.remoteapi.ui.theme.RemoteAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                  RemoteAPITheme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                              AppNavigation(Modifier.padding(innerPadding))
                        }
                  }
            }
      }
}