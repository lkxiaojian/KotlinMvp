package com.example.lk.kotlinframework.adapter.base

import android.content.Context

/**
 * Created by lk on 2018/6/8.
 */
class BaseAdapterIml<T>( context: Context,  list: MutableList<T>, var layout: Int) :
        BaseDataRecycleAdapter<T>(context, list) {

    override val layoutId: Int
        get() = layout

}
