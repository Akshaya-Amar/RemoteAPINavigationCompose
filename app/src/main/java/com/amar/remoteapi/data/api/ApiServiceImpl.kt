package com.amar.remoteapi.data.api

import com.amar.remoteapi.data.model.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
      private val client: HttpClient
) : ApiService {
      override suspend fun getPosts(): List<Post> {
            return try {
                  client.get {
                        url("https://jsonplaceholder.typicode.com/posts")
                  }.body()
            } catch (exception: Exception) {
                  emptyList()
            }
      }
}