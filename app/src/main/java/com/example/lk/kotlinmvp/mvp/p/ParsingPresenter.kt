package com.example.lk.kotlinmvp.mvp.p

import android.content.Context
import com.example.lk.kotlinframework.mvp.m.moudle.HomeModel
import com.example.lk.kotlinmvp.mvp.m.bean.FindBean
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

    override fun <T> start(type: String, map: HashMap<*, *>?) {
        requestData<T>(type, map)
    }

    override fun <T> requestData(type: String, map: HashMap<*, *>?) {
        when (type) {
            "loadData" -> {
                val observable: Observable<T>? = mContext?.let { mModel.loadData(it, true, "0") }
                observable?.applySchedulers()?.subscribe { bean: T ->
                    mView?.setData(type, bean)
                }

            }
            "findFragment" ->{
                val observable : Observable<T>? = mContext?.let { mModel.FindData(mContext!!) }
                observable?.applySchedulers()?.subscribe { beans : T ->
                    mView?.setData(type,beans)
                }
            }
        }
    }

    fun <T> moreData(data: String?) {
        val observable: Observable<T>? = mContext?.let { mModel.loadData(it, false, data) }
        observable?.applySchedulers()?.subscribe { bean: T ->
            mView?.setData("loadData", bean)
        }
    }


}
