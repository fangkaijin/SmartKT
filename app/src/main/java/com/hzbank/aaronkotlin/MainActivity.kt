package com.hzbank.aaronkotlin

import android.os.Bundle
import android.view.LayoutInflater
import com.hzbank.aaronkotlin.databinding.ActivityHomeBinding

class MainActivity : BaseActivity<ActivityHomeBinding>() {

    override fun getBinding() =
        ActivityHomeBinding.inflate(LayoutInflater.from(this))

    override fun parseView() {

        xBing?.tag?.setText("hello, Aaron-kotlin")
    }
}