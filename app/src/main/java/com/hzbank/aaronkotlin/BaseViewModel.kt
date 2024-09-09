package com.hzbank.aaronkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BaseViewModel: ViewModel() {

    private val _stateStringFlow = MutableStateFlow<String>("")
    val stateFlow = _stateStringFlow.asStateFlow()

    private val __stateApiFlow = MutableStateFlow<WeatherBean>(WeatherBean());
    val stateApiFlow = __stateApiFlow.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        //清空资源
    }

    fun doOperator01(){
        "doOperator01开始执行".showLog()
        _stateStringFlow.value = ""

        viewModelScope.launch(Dispatchers.IO){

            delay(10000)

            withContext(Dispatchers.Main){
                "doOperator01回调执行".showLog()
                //回调数据
                _stateStringFlow.value = "Hello, 执行协程第一个方法";

            }

        }

    }

    fun doOperator02(api: String, params: JsonObject){

        __stateApiFlow.value = WeatherBean()

        viewModelScope.async(Dispatchers.IO) {

            val result: WeatherBean? = OkHttpUtils.getInstance().requestGet<WeatherBean>(api, params)

            withContext(Dispatchers.Main){

                //处理结果
                if(null == result){

                    val errorBean = WeatherBean()
                    errorBean.code = "-9999"
                    errorBean.msg = "接口请求失败"
                    __stateApiFlow.value = errorBean
                } else {

                    __stateApiFlow.value = result
                }

            }

        }

    }
}