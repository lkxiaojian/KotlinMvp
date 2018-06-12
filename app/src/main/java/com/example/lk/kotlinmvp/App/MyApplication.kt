package com.example.lk.kotlinmvp.App

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

/**
 * Created by lk on 2018/6/12.
 */
class MyApplication: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }


}