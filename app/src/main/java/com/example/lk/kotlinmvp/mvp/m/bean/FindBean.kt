package com.example.lk.kotlinmvp.mvp.m.bean

import android.view.View
import com.example.urilslibrary.Utils
import java.util.jar.Attributes

/**
 * Created by lk on 2018/6/12.
 */
/**
 * id : 36
 * name : 生活
 * alias : null
 * description : 匠心、健康、生活感悟
 * bgPicture : http://img.kaiyanapp.com/924ebc6780d59925c8a346a5dafc90bb.jpeg
 * bgColor :
 * headerImage : http://img.kaiyanapp.com/a1a1bf7ed3ac906ee4e8925218dd9fbe.png
 */
data class FindBean(var id: Int, var name: String?, var alias: Any?,
                    var description: String?, var bgPicture: String?,
                    var bgColor: String?, var headerImage: String?) {



        fun Onclick(view: View) {
            Utils.ToastShort(view.context, this.name)
        }



}

