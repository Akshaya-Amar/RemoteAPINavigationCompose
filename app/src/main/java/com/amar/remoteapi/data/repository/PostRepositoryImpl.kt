package com.amar.remoteapi.data.repository

import com.amar.remoteapi.common.ApiResult
import com.amar.remoteapi.data.api.ApiService
import com.amar.remoteapi.data.model.Post
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
      private val apiService: ApiService
) : PostRepository {
      override suspend fun getPosts(): Flow<ApiResult<List<Post>>> = flow {
            try {
                  val response = apiService.getPosts()
                  emit(ApiResult.Success(response))
            } catch (redirectResponseException: RedirectResponseException) {
                  emit(ApiResult.Failure("Redirect error: ${redirectResponseException.response.status.value}, ${redirectResponseException.response.status.description}"))
            } catch (clientRequestException: ClientRequestException) {
                  emit(ApiResult.Failure("Client error: ${clientRequestException.response.status.value}, ${clientRequestException.response.status.description}"))
            } catch (serverResponseException: ServerResponseException) {
                  emit(ApiResult.Failure("Server error: ${serverResponseException.response.status.value}, ${serverResponseException.response.status.description}"))
            } catch (exception: Exception) {
                  emit(ApiResult.Failure("Unexpected error: ${exception.message ?: "Unknown"}"))
            }
      }.flowOn(Dispatchers.IO)
}