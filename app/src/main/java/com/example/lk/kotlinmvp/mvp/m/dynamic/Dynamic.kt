package com.example.lk.kotlinmvp.mvp.m.dynamic

import android.util.Log
import java.lang.reflect.Method


/**
 * Created by lk on 2018/7/18.
 * 通过反射 来动态处理网络请求
 */
class Dynamic {
    companion object {
        fun <T> invoke(className: String, methodName: String, vararg value: Any): T? {
            val clazz = Class.forName(className)
            val instance = clazz.newInstance()
            val methods = clazz.declaredMethods
            var executeMethods: Method? = null

            for (method in methods) {
                if (method.name == methodName) {
                    executeMethods = method
                    break
                }
            }
            executeMethods?.isAccessible = true
            Log.e("executeMethods", "executeMethods--" + executeMethods)
            return executeMethods?.invoke(instance, value) as T?
        }
    }
}