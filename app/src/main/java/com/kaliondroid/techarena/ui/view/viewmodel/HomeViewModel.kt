package com.kaliondroid.techarena.ui.view.viewmodel;

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.kaliondroid.techarena.data.models.NewsItem
import com.kaliondroid.techarena.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {


    private val _techNews = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsStateFlow: StateFlow<List<NewsItem>> = _techNews

    private val _errorMessage = MutableStateFlow("UnKnown Error")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        Log.d("TAG", "HomeViewModel Initialized ")
        getTechNews()
    }

    private fun getTechNews() {
        viewModelScope.launch {
            repository.getTechNews()
                .collect { response ->
                    when (response) {
                        is NetworkResponse.Success -> {
                            _techNews.value =
                                response.body.data ?: emptyList()
                            Log.d("HomeViewModel", "getTechNews: Success ${response.body.data}")
                        }

                        is NetworkResponse.Error -> {
                            _errorMessage.value =
                                response.error?.message ?: "UnKnown Error"
                            Log.d("HomeViewModel", "getTechNews: Error ${response.error?.message}")
                        }
                    }
                }
        }
    }
}
