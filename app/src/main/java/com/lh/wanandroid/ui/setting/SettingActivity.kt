package com.lh.wanandroid.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.color.ColorChooserDialog
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseActivity
import com.lh.wanandroid.utils.SettingUtil
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Author: lh
 * Description: 设置界面，主要加载setting的fragment
 * Data: 2020/9/15
 */
class SettingActivity: BaseActivity(), ColorChooserDialog.ColorCallback  {

    override fun attachLayoutRes() = R.layout.activity_setting

    override fun initData() {

    }

    override fun initView() {

        initToolbar(toolbar, resources.getString(R.string.setting), true)

//        val fragment = Fragment.instantiate(this, SettingFragment::class.java.name, Bundle())

        val fragment = SettingFragment.get(this)
        supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commitAllowingStateLoss()


    }

    override fun start() {
    }

    override fun onColorSelection(dialog: ColorChooserDialog, selectedColor: Int) {
        if (!dialog.isAccentMode){
            SettingUtil.setColor(selectedColor)
        }

        initColor()
    }

    override fun onColorChooserDismissed(dialog: ColorChooserDialog) {
    }
}