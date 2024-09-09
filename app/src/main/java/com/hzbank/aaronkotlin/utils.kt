package com.hzbank.aaronkotlin

import android.view.View
import android.widget.Toast

fun View.toast(msg: String){
    this?.let {


        Toast.makeText(it.context, msg, Toast.LENGTH_SHORT).show()

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