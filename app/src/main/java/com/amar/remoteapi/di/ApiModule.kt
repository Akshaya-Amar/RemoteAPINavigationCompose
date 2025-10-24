package com.amar.remoteapi.di

import com.amar.remoteapi.data.api.ApiService
import com.amar.remoteapi.data.api.ApiServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

      @Binds
      @Singleton
      abstract fun bindApiService(
            impl: ApiServiceImpl
      ): ApiService
}