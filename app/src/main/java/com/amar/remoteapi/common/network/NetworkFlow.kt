package com.amar.remoteapi.common.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> networkFlow(
      apiCall: suspend () -> T
): Flow<ApiResult<T>> = flow {
      emit(safeApiCall { apiCall() })
}.flowOn(Dispatchers.IO)