package com.hzbank.aaronkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hzbank.aaronkotlin.databinding.ViewKdbBigImgShowBinding

class KdbImgShowView(context: Context):
    BaseCustomView<ViewKdbBigImgShowBinding>(context) {
    override fun getBinding(
        inflater: LayoutInflater
    ): ViewKdbBigImgShowBinding? = ViewKdbBigImgShowBinding.inflate(inflater, this, true)

    override fun parseView(binding: ViewKdbBigImgShowBinding?) {

        binding?.let {

            it.closePreBtn.setOnClickListener {

                //关闭
                callback?.let {

                    it.closeBigPic()
                }
            }

        }

    }

    private lateinit var callback: KdbImgShowCallback

    fun addCallback(callback: KdbImgShowCallback){

        this.callback = callback
    }

    fun showBigPic(picPath: String, isLoal: Boolean, picW: Int, picH: Int){
    }


    interface KdbImgShowCallback{

        fun closeBigPic()

    }

}