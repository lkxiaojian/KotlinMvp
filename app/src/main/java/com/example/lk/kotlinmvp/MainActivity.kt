package com.example.lk.kotlinmvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.lk.kotlinmvp.ui.fragment.HomeFragment
import com.example.lk.kotlinmvp.ui.fragment.HotFragment
import com.example.lk.kotlinmvp.ui.fragment.MineFragment
import com.example.lk.kotlinmvp.ui.fragment.FindFragment
import com.gyf.barlibrary.ImmersionBar

class MainActivity : AppCompatActivity() {
    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragemnt: HotFragment? = null
    var mineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initview()
        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is FindFragment) {
                    findFragment = item
                }
                if (item is HotFragment) {
                    hotFragemnt = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            mineFragment = MineFragment()
            hotFragemnt = HotFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, homeFragment)
            fragmentTrans.add(R.id.fl_content, findFragment)
            fragmentTrans.add(R.id.fl_content, mineFragment)
            fragmentTrans.add(R.id.fl_content, hotFragemnt)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(findFragment)
                .hide(mineFragment)
                .hide(hotFragemnt)
                .commit()
    }

    /**
     * 4.4以上沉浸式以及bar的管理
     */
    private fun initview() {
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params

    }
}
