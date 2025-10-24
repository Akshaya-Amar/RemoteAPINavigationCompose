package com.amar.remoteapi.di

import com.amar.remoteapi.common.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
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
                        json(Json {
                              ignoreUnknownKeys = true
                        })
                  }

                  defaultRequest {
                        url(BASE_URL)
                  }
            }
      }
}