package com.example.lk.kotlinmvp.mvp.p

import android.content.Context
import com.example.lk.kotlinframework.mvp.m.moudle.HomeModel
import com.example.lk.kotlinmvp.mvp.m.dynamic.Dynamic
import com.example.lk.kotlinmvp.mvp.v.Contract
import com.example.lk.kotlinmvp.uitls.applySchedulers
import io.reactivex.Observable

/**
 * Created by lk on 2018/6/8.
 */
class ParsingPresenter(view: Contract.View) : Contract.Presenter {


    var mView: Contract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
    }

    override fun <T> start(vararg value: Any) {
        if (value == null || value.size < 2) {
            return
        }
        var type: String = value[0] as String
        var method: String = value[1] as String
        var url: String?=null
        if (value.size > 2) {
            url = value[2] as String
        }

        var map: HashMap<*, *>? = null
        if (value.size == 4) {
            map = value[3] as HashMap<*, *>
        }
        requestData<T>(type, method, url, map)
    }


    override fun <T> requestData(type: String, method: String, url: String?, map: HashMap<*, *>?) {

        val observable: Observable<T>? = let { Dynamic.invoke(mModel.javaClass.name, method) }
        CustomData(observable, type)
    }

    fun <T> moreData(data: String?, url: String, type: String, map: HashMap<*, *>?) {

        when (type) {
            "loadData" -> {
                val observable: Observable<T>? = let { mModel.loadData(false, data!!) }
                CustomData(observable, type)
            }
        }
    }

    fun <T> CustomData(observable: Observable<T>?, type: String) {
        if (mView == null) {
            return
        }
        observable?.applySchedulers()?.subscribe({ beans: T ->
            mView?.setData(type, beans)
        }, { error: Throwable ->
            mView?.onError(type, error)
        })

    }
}


