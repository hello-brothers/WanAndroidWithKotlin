package com.lh.wanandroid.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Author: lh
 * Description: 设置界面，主要加载setting的fragment
 * Data: 2020/9/15
 */
class SettingActivity: BaseActivity() {

    override fun attachLayoutRes() = R.layout.activity_setting

    override fun initData() {

    }

    override fun initView() {

        initToolbar(toolbar, resources.getString(R.string.setting), true)

        val fragment = Fragment.instantiate(this, SettingFragment::class.java.name, Bundle())

        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commitAllowingStateLoss()


    }

    override fun start() {
    }
}