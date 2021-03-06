package com.example.lk.kotlinframework.network

import android.content.Context
import android.util.Log
import com.example.lk.kotlinmvp.App.MyApplication
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by lk on 2018/6/8.
 */
class RetrofitClient {
    var httpCacheDirectory: File? = null
    val mContext: Context = MyApplication.getAppContext()
    var cache: Cache? = null
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 20
    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
//            Log.e("OKHttp", "Could not create http cache--"+ e)
        }
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(getLogInterceptor())
                .addNetworkInterceptor(CacheInterceptor(mContext))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.BASE_URL)
                .build()

    }


    /**
     * 日志拦截
     */
    fun getLogInterceptor(): HttpLoggingInterceptor {
        // 日志显示级别
        val level = HttpLoggingInterceptor.Level.BODY
        // 新建日志拦截器
        val loggingInterceptor = HttpLoggingInterceptor { message -> Log.e("ApiUrl", "--->$message") }
        loggingInterceptor.level = level
        return loggingInterceptor
    }


    companion object {
        fun getInstance(): RetrofitClient {
            return SingletonHolder.holder
        }

    }


    private object SingletonHolder {
        val holder = RetrofitClient()
    }


    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }


}