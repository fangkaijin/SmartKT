package com.hzbank.aaronkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<out T: ViewBinding>: AppCompatActivity() {

    private lateinit var _binding: T

    val xBing: T?
        get() {
            try{

                if(this::_binding.isLateinit){

                    return this::_binding.get()
                }

                return null

            }catch (e: Exception){
                e.printStackTrace()
            }

            return null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = getBinding()
        setContentView(_binding.root)

        parseView()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getBinding(): T
    abstract fun parseView()

}