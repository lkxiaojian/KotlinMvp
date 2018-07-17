package com.example.lk.kotlinmvp.App

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.example.lk.kotlinmvp.App.MyApplication.Companion.mAppContext

/**
 * Created by lk on 2018/6/12.
 */
class MyApplication : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        mAppContext = applicationContext
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    /**
     * 获取系统上下文
     */
    companion object {
        var mAppContext: Context? = null
        fun getAppContext(): Context {
            return mAppContext!!
        }
    }


}