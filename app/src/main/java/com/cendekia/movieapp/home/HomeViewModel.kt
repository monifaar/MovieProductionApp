package com.cendekia.movieapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cendekia.movieapp.core.domain.usecase.WatchesUseCase

class HomeViewModel(watchesUseCase: WatchesUseCase) : ViewModel() {
    val watchess = watchesUseCase.getAllWatches().asLiveData()
}

