package com.amar.remoteapi.data.repository

import com.amar.remoteapi.common.ApiResult
import com.amar.remoteapi.data.api.ApiService
import com.amar.remoteapi.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
      private val apiService: ApiService
) : PostRepository {
      override suspend fun getPosts(): Flow<ApiResult<List<Post>>> = flow {
            emit(ApiResult.Success(apiService.getPosts()))
      }.flowOn(Dispatchers.IO)
}