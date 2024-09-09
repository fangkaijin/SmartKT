package com.hzbank.aaronkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out T: ViewBinding>: Fragment() {

    private var binding: T? = null
        get() = binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, container)

        return binding?.root?:null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        //销毁
        binding = null

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getBinding(inflater: LayoutInflater,
                            container: ViewGroup?): T?
    abstract fun parseView()
}