package com.hzbank.aaronkotlin

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseViewModel: ViewModel() {

    private val _stateStringFlow = MutableStateFlow<String>("")
    val stateFlow = _stateStringFlow.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        //清空资源
    }

    fun doOperator01(){

        viewModelScope.launch(Dispatchers.IO){

            delay(10000)

            withContext(Dispatchers.Main){

                //回调数据
                _stateStringFlow.value = "Hello, 执行协程第一个方法";

            }

        }

    }
}