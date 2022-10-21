package com.kaliondroid.techarena.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kaliondroid.techarena.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: NewsRepository
) : ViewModel() {

    val data = repository.getNewsList().cachedIn(viewModelScope)
}
