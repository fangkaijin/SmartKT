package com.hzbank.aaronkotlin

import android.os.Build
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.hzbank.aaronkotlin.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.withContext
import java.time.Duration
import java.time.temporal.TemporalUnit
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityHomeBinding>() {

    override fun getBinding() =
        ActivityHomeBinding.inflate(LayoutInflater.from(this))

    override fun parseView() {

        xBing?.showTips?.setText("Hello, AaronKotlin")

        xBing?.operator01?.setOnClickListener{

            it.toast("执行协程")

            lifecycleScope.launch(Dispatchers.IO){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    delay(Duration.ofSeconds(10))
                }else{
                    kotlinx.coroutines.delay(10000)
                }

                withContext(Dispatchers.Main){

                    xBing?.showTips?.setText("Hello, 执行协程第一个方法")

                }

            }

        }
    }
}