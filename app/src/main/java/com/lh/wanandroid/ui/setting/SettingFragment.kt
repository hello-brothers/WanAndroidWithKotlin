package com.lh.wanandroid.ui.setting

import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.preference.PreferenceFragmentCompat
import com.lh.wanandroid.R

/**
 * Author: lh
 * Description: 设置fragment
 * Data: 2020/9/15
 */
class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //加载xml
        addPreferencesFromResource(R.xml.pre_setting)
    }

}