package com.hzbank.aaronkotlin

import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.hzbank.aaronkotlin.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.temporal.TemporalUnit
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityHomeBinding>() {

    //创建 viewmodel
    private val viewModel: BaseViewModel by viewModels<BaseViewModel>()

    override fun getBinding() =
        ActivityHomeBinding.inflate(LayoutInflater.from(this))

    override fun parseView() {

        xBing?.showTips?.setText("Hello, AaronKotlin")

        xBing?.operator01?.setOnClickListener{

            it.toast("执行协程")
            xBing?.operator01?.showLoading(LoadingDialog.getInstance(this), true)

            viewModel.doOperator01()

        }

        xBing?.operator02?.setOnClickListener {

            it.toast("接口请求中")
            xBing?.operator02?.showLoading(LoadingDialog.getInstance(this), true)

            val params = JsonObject();
            params.addProperty("name", "杭州市")

            viewModel.doOperator02("api/weather", params)

        }

        lifecycleScope.launch(Dispatchers.Main) {

            viewModel.stateApiFlow.collect{

                it?.let {

                    if(!TextUtils.isEmpty(it.code)){

                        if(TextUtils.equals("200", it.code)){
                            xBing?.operator02?.toast("获取天气成功")
                        }else{

                            xBing?.operator02?.toast("获取天气失败")
                        }

                    }


                }

                xBing?.operator02?.showLoading(LoadingDialog.getInstance(this@MainActivity), false)

            }

        }

        lifecycleScope.launch(Dispatchers.Main){

            viewModel.stateFlow.collect{

                ("viewModel数据收集="+it).showLog()
                if(!TextUtils.isEmpty(it)){


                    xBing?.showTips?.setText(it)

                    xBing?.operator01?.showLoading(LoadingDialog.getInstance(this@MainActivity), false)
                }
            }

        }
    }
}