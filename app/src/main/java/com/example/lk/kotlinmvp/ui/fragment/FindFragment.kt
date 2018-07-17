package com.example.lk.kotlinmvp.ui.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lk.kotlinmvp.R
import com.example.lk.kotlinmvp.databinding.FindHomeBinding
import com.example.lk.kotlinmvp.mvp.m.bean.FindBean
import com.example.lk.kotlinmvp.mvp.p.ParsingPresenter
import com.example.lk.kotlinmvp.mvp.v.Contract
import com.example.lk.kotlinmvp.ui.fragment.base.BaseFragment
import java.util.ArrayList

/**
 * Created by lk on 2018/6/12.
 */
class FindFragment : BaseFragment() {
    override fun onError(type: String, error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var mPresenter: ParsingPresenter? = null
    var mList: MutableList<FindBean>? = null
    var binding: FindHomeBinding? = null
    override fun <T> setData(type: String, bean: T) {
        if (!"findFragment".equals(type)) {
            return
        }
        mList = bean as MutableList<FindBean>
        binding?.data = mList as ArrayList<FindBean>

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.find_home, container, false) as FindHomeBinding
        return binding?.root
    }

    override fun getLayoutView(): View {
        return binding!!.root
    }

    override fun initView() {
        mPresenter = ParsingPresenter(this)
        mPresenter?.start<MutableList<FindBean>>("findFragment", "")
        binding?.data = mList as ArrayList<FindBean>?
    }

}