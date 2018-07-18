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

    /**
     * value[0]  返回类型  多个请求同时进行 ，根据此字段来判断
     * value[1]  请求网络的方法名
     * value[2]  如果是用rf  直接用 URL 来请求的url地址
     * value[3]  如果 是请求 网络的时候，有表单  此map为表单
     * value[4]  用 rf 请求所带的参数     如 fun <T> getHomeMoreData(@Query("date") date: String, @Query("num") num: String): Observable<HomeBean>
     * date  num  传递的就是这两个的值
     */
    override fun <T> start(vararg value: Any) {
        if (value == null || value.size < 2) {
            return
        }
        var type: String = value[0] as String
        var method: String = value[1] as String
        var url: String? = null
        if (value.size > 2) {
            url = value[2] as String
        }

        var map: HashMap<*, *>? = null
        if (value.size == 4) {
            map = value[3] as HashMap<*, *>
        }
        requestData<T>(type, method, url, map)
    }

    /**
     * 传递参数 通过map的方式
     */
    override fun <T> start(value: HashMap<String, Any>) {

        requestData<T>(
                value.get("type") as String,//返回类型  多个请求同时进行 ，根据此字段来判断
                value.get("method") as String,  //请求网络的方法名
                value.get("url") as? String,  //如果是用rf  直接用 URL 来请求的url地址
                value.get("map") as? HashMap<*, *>,//如果 是请求 网络的时候，有表单  此map为表单
                value.get("param") //用 rf 请求所带的参数     如 fun <T> getHomeMoreData(@Query("date") date: String, @Query("num") num: String): Observable<HomeBean>
                //date  num  传递的就是这两个的值
        )
    }

    override fun <T> requestData(type: String, method: String, url: String?, map: HashMap<*, *>?, vararg param: Any?) {

        val observable: Observable<T>? = let { Dynamic.invoke(mModel.javaClass.name, method, param) }
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


