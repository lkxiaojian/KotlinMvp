package com.example.lk.kotlinframework.mvp.p.base
/**
 * Created by lk on 2018/6/8.
 */
interface BasePresenter {
    fun<T> start(type:String,url:String,map:HashMap<*,*>?)
}