package com.example.lk.kotlinmvp.mvp.v

import com.example.lk.kotlinframework.mvp.m.bean.HomeBean
import com.example.lk.kotlinframework.mvp.p.base.BasePresenter
import com.example.lk.kotlinframework.mvp.v.base.BaseView
import java.util.*

/**
 * Created by lk on 2018/6/8.
 */
interface Contract {
    interface View : BaseView<Presenter> {
        fun <T> setData(type: String, bean: T)
        fun  onError( type: String,error: Throwable)
    }

    interface Presenter : BasePresenter {
        fun <T> requestData(type: String, url: String, map: HashMap<*, *>?)
    }

}