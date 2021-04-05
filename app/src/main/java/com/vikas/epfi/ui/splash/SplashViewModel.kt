package com.vikas.epfi.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.epfi.common.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *<h1>SplashViewModel</h1>
 * @author : Vikas
 * @since : 3 April 2021
 * @version : 1.0
 */
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time


    fun initStart() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.SPLASH_DELAY_MILLIS)
            postRouteEvent(true)
        }
    }

    private fun postRouteEvent(isTime: Boolean) {
        _time.postValue(isTime)
    }
}