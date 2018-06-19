package com.example.lk.kotlinframework.adapter.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lk.kotlinframework.viewholder.BindingHolder
import com.example.lk.kotlinframework.viewholder.ViewHolder
import com.example.lk.kotlinmvp.BR
/**
 * Created by lk on 2018/5/7.
 */

abstract class BaseDataRecycleAdapter<T>(var mContext: Context, var data: MutableList<T>?) : RecyclerView.Adapter<BindingHolder>() {
    var mOnItemClickListener: OnItemClickListener? = null

    /**
     * 重写此方法，来判断是否多类型的 默认是单类型
     *
     * @return
     */
    val isMoreType: Boolean
        get() = false

    //获取data
    val datas: List<T>?
        get() = data

    // 获取布局文件
    abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val holder = ViewHolder.createViewHolder(mContext, parent, layoutId)
        setListener(holder, viewType)
        if (isMoreType) {//多类型
            return ViewHolderConvert(parent, viewType)
        }
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(mContext), layoutId, parent, false)
        return BindingHolder(dataBinding)
    }

    /**
     * 多类型的话，重写此方法和isMoreType()返回true,
     *
     * @param parent
     * @param viewType
     * @return
     */
    fun ViewHolderConvert(parent: ViewGroup, viewType: Int): BindingHolder {

        return ViewHolderConvert(parent, viewType)
    }

    /**
     * item的点击监听 和长按监听  根据需要来继承，databinding一般不需要
     *
     * @param viewHolder
     * @param viewType
     */
    fun setListener(viewHolder: ViewHolder, viewType: Int) {
        //来判断某个item点击是否有效,默认是无效的
        if (!isEnabled(viewType)) return

        viewHolder.convertView.setOnClickListener({ v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.getAdapterPosition()
                mOnItemClickListener!!.onItemClick(v, viewHolder, position)
            }
        })

        viewHolder.convertView.setOnLongClickListener({ v ->
            if (mOnItemClickListener != null) {
                val position = viewHolder.getAdapterPosition()
                mOnItemClickListener!!.onItemLongClick(v, viewHolder, position)
            }
            false
        })

    }

    /**
     * 重写此方法，来判断某个item点击是否有效
     *
     * @param viewType
     * @return
     */
    fun isEnabled(viewType: Int): Boolean {
        return true
    }


    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding.setVariable(BR.bean, data!![position])
       // 立刻刷新界面
        holder.binding.executePendingBindings()
        convert(holder, data!![position])

    }

    /**
     * 把onBindViewHolder映射出去，每个adapter可以重写此方法
     *
     * @param holder
     * @param t
     */
    fun convert(holder: BindingHolder, t: T) {

    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    /**
     * 删除某条数据
     *
     * @param position
     */
    fun removeData(position: Int) {
        notifyItemRemoved(position + 1)
        if (position != data!!.size) {
            if (position == 0) {
                data!!.removeAt(position)
                notifyDataSetChanged()
            } else if (position == data!!.size - 1) {
                data!!.removeAt(position)
                notifyItemRangeChanged(position, 0)
            } else {
                data!!.removeAt(position)
                notifyItemRangeChanged(position, data!!.size - position + 1)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, holder: RecyclerView.ViewHolder, position: Int)

        fun onItemLongClick(view: View, holder: RecyclerView.ViewHolder, position: Int): Boolean
    }


}
