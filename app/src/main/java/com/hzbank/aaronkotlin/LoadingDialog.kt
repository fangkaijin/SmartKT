package com.hzbank.aaronkotlin

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.hzbank.aaronkotlin.databinding.LayoutDialogBinding

class LoadingDialog : Dialog {

    private lateinit var bind: LayoutDialogBinding

    private constructor(context: Context): super(context){
        initView(context)
    }

    private constructor(context: Context, themeStyle: Int): super(context, themeStyle){
        initView(context)
    }

    private fun initView(context: Context){

        bind = LayoutDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(bind.root)
        setCanceledOnTouchOutside(false)

        bind.loadingTips.setText("数据加载中...")
    }

    fun showLoding(){

        if(!this.isShowing) show()
    }

    fun hiddenLoading(){

        if(this.isShowing) this.hide()
        this.cancel()
    }

    companion object{

        private var dialogInstance: LoadingDialog? = null

        fun getInstance(context: Context): LoadingDialog{
            return dialogInstance?: synchronized(this){
                dialogInstance?:LoadingDialog(context).also { dialogInstance = it }
            }

        }

    }


}