package com.example.lk.kotlinframework.mvp.m.moudle

import android.content.Context
import com.example.lk.kotlinframework.mvp.m.bean.HomeBean

import com.example.lk.kotlinframework.network.ApiService
import com.example.lk.kotlinframework.network.RetrofitClient
import com.example.lk.kotlinmvp.mvp.m.bean.FindBean
import io.reactivex.Observable

/**
 * Created by lk on 2018/6/8.
 */
class HomeModel {

    val retrofitClient = RetrofitClient.getInstance()
    val apiService = retrofitClient.create(ApiService::class.java)
    fun <T> loadData(vararg value: Any?): Observable<T>? {
        val retrofitClient = RetrofitClient.getInstance()
        val apiService = retrofitClient.create(ApiService::class.java)
        var isfalg = false
        if (value.size == 0) {
            isfalg = true
        }
        when (isfalg) {
            true -> return apiService?.getHomeData<HomeBean>() as Observable<T>
            false -> return apiService?.getHomeMoreData<HomeBean>(value[1] as String, "2") as Observable<T>
        }
    }


    fun <T> FindData(vararg value: Any?): Observable<T>? {
        return apiService?.getFindData() as Observable<T>
    }
}