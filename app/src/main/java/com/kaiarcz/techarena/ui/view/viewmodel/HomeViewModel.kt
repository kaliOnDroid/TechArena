package com.kaiarcz.techarena.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kaiarcz.techarena.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: NewsRepository
) : ViewModel() {

    val data = repository.getNewsList().cachedIn(viewModelScope)
}
