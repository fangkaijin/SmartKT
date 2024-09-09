package com.hzbank.aaronkotlin

import android.app.Dialog
import android.content.Context
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<T: ViewBinding>: Dialog {

    var bind: T? = null

    constructor(context: Context): super(context){
        initView(context)
    }

    constructor(context: Context, themeStyle: Int): super(context, themeStyle){
        initView(context)
    }


    private fun initView(context: Context){

        bind = getBinding()
        setContentView(bind!!.root)
        setCanceledOnTouchOutside(false)
        parseView()
    }

    fun showLoding(){

        if(!this.isShowing) show()
    }

    fun hiddenLoading(){

        if(this.isShowing) this.hide()
        this.cancel()
    }

    abstract fun getBinding(): T
    abstract fun parseView()

}