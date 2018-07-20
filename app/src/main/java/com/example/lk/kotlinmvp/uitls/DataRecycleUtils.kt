package com.example.lk.kotlinmvp.uitls

import android.databinding.BindingAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.example.lk.kotlinframework.adapter.base.BaseAdapterIml

import com.example.lk.kotlinmvp.R
import com.example.lk.kotlinmvp.mvp.m.bean.FindBean

/**
 * Created by lk on 2018/6/11.
 */

class DataRecycleUtils {
    companion object {
        @BindingAdapter("find")
        @JvmStatic
        fun setfindadapter(recyclerView: RecyclerView, data: MutableList<FindBean>?) {
            if (data == null) {
                return
            }
            recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
            val layout = R.layout.find_item
            val adapter = BaseAdapterIml(recyclerView.context, data, layout)
            recyclerView.adapter = adapter
        }
    }
}
