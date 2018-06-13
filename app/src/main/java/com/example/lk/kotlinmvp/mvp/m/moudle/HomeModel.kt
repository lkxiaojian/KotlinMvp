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


    fun <T> loadData(context: Context, isFirst: Boolean, data: String?): Observable<T>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        when (isFirst) {
            true -> return apiService?.getHomeData<HomeBean>() as Observable<T>
            false -> return apiService?.getHomeMoreData<HomeBean>(data.toString(), "2") as Observable<T>
        }
    }

    fun<T> FindData(context: Context): Observable<T>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData() as Observable<T>
    }
}