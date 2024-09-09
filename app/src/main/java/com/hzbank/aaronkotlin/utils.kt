package com.hzbank.aaronkotlin

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

fun View.toast(msg: String){
    this?.let {


        Toast.makeText(it.context, msg, Toast.LENGTH_SHORT).show()

    }
}

fun String.toast(context: Context){
    context?.let {

        Toast.makeText(it, this, Toast.LENGTH_SHORT).show()
    }

}

fun View.showLoading(dialog: LoadingDialog, isShow: Boolean){

    this?.let {

        if(isShow) {
            dialog.showLoding()
        } else{
            dialog.hiddenLoading()
        }

    }
}

fun String.showLog(){
    this?.let {
        Log.d("fangkaijin", it?:"无log")
    }
}

fun Any.showLog(log: String){

    this?.let {
        Log.d("fangkaijin", log?:"无log")
    }

}