package com.amar.remoteapi.di

import com.amar.remoteapi.data.repository.PostRepository
import com.amar.remoteapi.data.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

      @Binds
      @Singleton
      abstract fun bindsPostRepository(
            impl: PostRepositoryImpl
      ): PostRepository
}