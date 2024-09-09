package com.hzbank.aaronkotlin

import android.view.View
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
import okhttp3.internal.wait

class BaseViewModel: ViewModel() {

    private val _stateStringFlow = MutableStateFlow<String>("")
    val stateFlow = _stateStringFlow.asStateFlow()

    private val __stateApiFlow = MutableStateFlow<BaseBean<weather>>(BaseBean());
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

        __stateApiFlow.value = BaseBean()

        viewModelScope.async(Dispatchers.IO) {

            val result: BaseBean<weather>? = OkHttpUtils.getInstance().requestPost<BaseBean<weather>>(api, params)

            withContext(Dispatchers.Main){

                //处理结果
                if(null == result){

                    val errorBean = BaseBean<weather>()
                    errorBean.code = "-9999"
                    errorBean.msg = "接口请求失败"
                    errorBean.data = null
                    __stateApiFlow.value = errorBean
                } else {

                    __stateApiFlow.value = result
                }

            }

        }

    }
}