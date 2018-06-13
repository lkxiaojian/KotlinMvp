package com.example.lk.kotlinmvp.uitls;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lk.kotlinframework.adapter.base.BaseAdapterIml;

import com.example.lk.kotlinmvp.R;
import com.example.lk.kotlinmvp.mvp.m.bean.FindBean;

import java.util.List;

/**
 * Created by lk on 2018/6/11.
 */

public class DataRecycleUtils {
    @BindingAdapter("find")
    public static void setfindadapter(RecyclerView recyclerView, List<FindBean> data) {
        if (data == null) {
            return;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2));
        int layout = R.layout.find_item;
        BaseAdapterIml adapter = new BaseAdapterIml(recyclerView.getContext(), data, layout);
        recyclerView.setAdapter(adapter);
    }

}
