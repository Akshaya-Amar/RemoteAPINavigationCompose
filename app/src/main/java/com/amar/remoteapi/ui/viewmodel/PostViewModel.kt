package com.amar.remoteapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amar.remoteapi.common.network.ApiResult
import com.amar.remoteapi.data.model.Post
import com.amar.remoteapi.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
      private val repository: PostRepository
) : ViewModel() {
      private var _posts = MutableStateFlow<ApiResult<List<Post>>>(ApiResult.Loading)
      val posts = _posts.asStateFlow()

      init {
            getPosts()
      }

      fun getPosts() {
            viewModelScope.launch {
                  _posts.value = ApiResult.Loading
                  repository.getPosts().collectLatest {
                        Log.d("check12...", "getPosts: ")
                        _posts.value = it
                  }
            }
      }
}