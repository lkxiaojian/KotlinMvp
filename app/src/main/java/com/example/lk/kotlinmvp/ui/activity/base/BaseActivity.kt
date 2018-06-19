package com.example.lk.kotlinmvp.ui.activity.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lk.kotlinmvp.mvp.v.Contract

/**
 * Created by lk on 2018/6/19.
 */
abstract class BaseActivity : AppCompatActivity(), Contract.View {

    override fun onStart() {
        super.onStart()
        initview()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun initview()
}