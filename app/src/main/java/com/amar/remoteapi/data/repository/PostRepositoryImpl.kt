package com.amar.remoteapi.data.repository

import com.amar.remoteapi.common.network.networkFlow
import com.amar.remoteapi.data.api.ApiService
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
      private val apiService: ApiService
) : PostRepository {
      override suspend fun getPosts() = networkFlow {
            apiService.getPosts()
      }
}