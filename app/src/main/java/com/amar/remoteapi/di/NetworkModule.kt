package com.amar.remoteapi.di

import com.amar.remoteapi.BuildConfig
import com.amar.remoteapi.common.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

      @Provides
      @Singleton
      fun provideKtorClient(): HttpClient {
            return HttpClient(CIO) {
                  expectSuccess = true

                  install(ContentNegotiation) {
                        json(Json { ignoreUnknownKeys = true })
                  }

                  install(Logging) {
                        /* // to print logs using custom tag
                        logger = object : Logger {
                              override fun log(message: String) {
                                    Log.d("KTOR_LOG", message)
                              }
                        }*/
                        logger = Logger.SIMPLE
                        level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
                  }

                  install(HttpTimeout) {
                        requestTimeoutMillis = 15000
                        connectTimeoutMillis = 15000
                        socketTimeoutMillis = 15000
                  }

                  defaultRequest {
                        url(BASE_URL)
                        accept(ContentType.Application.Json)
                        header(HttpHeaders.Authorization, "Bearer X_TOKEN")
                        header("x-api-key", "API_KEY")
                  }
            }
      }
}