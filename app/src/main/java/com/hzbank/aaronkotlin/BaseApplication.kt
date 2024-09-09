package com.hzbank.aaronkotlin

import android.app.Application

class BaseApplication(): Application() {

    override fun onCreate() {
        super.onCreate()

        app = this
    }

    companion object{

        lateinit var app: Application

    }
}