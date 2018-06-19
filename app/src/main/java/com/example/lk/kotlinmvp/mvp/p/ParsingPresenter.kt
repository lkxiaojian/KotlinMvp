package com.example.lk.kotlinmvp.mvp.p

import android.content.Context
import com.example.lk.kotlinframework.mvp.m.moudle.HomeModel
import com.example.lk.kotlinmvp.mvp.v.Contract
import com.example.lk.kotlinmvp.uitls.applySchedulers
import io.reactivex.Observable

/**
 * Created by lk on 2018/6/8.
 */
class ParsingPresenter(context: Context, view: Contract.View) : Contract.Presenter {


    var mContext: Context? = null
    var mView: Contract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun <T> start(type: String, url: String, map: HashMap<*, *>?) {
        requestData<T>(type, url, map)
    }

    override fun <T> requestData(type: String, url: String, map: HashMap<*, *>?) {
        when (type) {
            "loadData" -> {
                val observable: Observable<T>? = mContext?.let { mModel.loadData(it, true, "0") }
                CustomData(observable, type)
            }
            "findFragment" -> {
                val observable: Observable<T>? = mContext?.let { mModel.FindData(mContext!!) }
                CustomData(observable, type)
            }
        }
    }

    fun <T> moreData(data: String?,url: String,type: String,map: HashMap<*, *>?) {
        val observable: Observable<T>? = mContext?.let { mModel.loadData(it, false, data) }
        CustomData(observable, type)
//        observable?.applySchedulers()?.subscribe { bean: T ->
//            mView?.setData("loadData", bean)
//        }
    }

    fun <T> CustomData(observable: Observable<T>?, type: String) {
        observable?.applySchedulers()?.subscribe({ beans: T ->
            mView?.setData(type, beans)
        }, { error: Throwable ->
            mView?.setError(type, error)
        })

    }
}


