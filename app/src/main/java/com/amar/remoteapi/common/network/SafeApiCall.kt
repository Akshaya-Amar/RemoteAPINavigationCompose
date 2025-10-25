package com.amar.remoteapi.common.network

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(
      apiCall: suspend () -> T
) = try {
      ApiResult.Success(apiCall())
} catch (redirectResponseException: RedirectResponseException) {
      ApiResult.Failure("Redirect error -> ${redirectResponseException.response.status.value}, ${redirectResponseException.response.status.description}")
} catch (clientRequestException: ClientRequestException) {
      ApiResult.Failure("Client error -> ${clientRequestException.response.status.value}, ${clientRequestException.response.status.description}")
} catch (serverResponseException: ServerResponseException) {
      ApiResult.Failure("Server error -> ${serverResponseException.response.status.value}, ${serverResponseException.response.status.description}")
} catch (exception: Exception) {
      ApiResult.Failure("Unexpected error -> ${exception.message ?: "Something went wrong"}")
}