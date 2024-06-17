package com.lexleontiev.tapyou.test.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lexleontiev.tapyou.test.app.di.Injector
import com.lexleontiev.tapyou.test.app.remote.NetworkResult
import com.lexleontiev.tapyou.test.app.remote.PointRepository
import kotlinx.coroutines.launch


class ResultViewModel(
    private val pointRepository: PointRepository = Injector.inject(PointRepository::class)
): ViewModel() {

    private val _screenState = MutableLiveData<ResultScreenState>(ResultScreenState.Loading)
    val screenState: LiveData<ResultScreenState> = _screenState

    fun requestPoints(count: Int) {
        viewModelScope.launch {
            _screenState.value = ResultScreenState.Loading
            when (val result = pointRepository.getPoints(count)) {
                is NetworkResult.Success -> {
                    val sortedList = result.data.points.sortedBy { it.x }
                    _screenState.value = ResultScreenState.Data(sortedList)
                }
                is NetworkResult.Error, is NetworkResult.Exception -> {
                    _screenState.value = ResultScreenState.Error
                }
            }
        }
    }
}