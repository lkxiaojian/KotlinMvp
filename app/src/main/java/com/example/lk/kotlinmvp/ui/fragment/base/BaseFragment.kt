package com.example.lk.kotlinmvp.ui.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lk.kotlinmvp.mvp.v.Contract


abstract class BaseFragment : Fragment(), Contract.View {
    var isFirst: Boolean = false
    var rootView: View? = null
    var isFragmentVisiable: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = getLayoutView()
        initView()
    }



    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isFragmentVisiable = true
        }
        if (rootView == null) {
            return
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisiable) {
            onFragmentVisiableChange(true)
            return
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisiable) {
            onFragmentVisiableChange(false)
            isFragmentVisiable = false
        }
    }

    open protected fun onFragmentVisiableChange(b: Boolean) {

    }


    //    abstract fun getLayoutResources(): Int
    abstract fun getLayoutView(): View

    abstract fun initView()
}