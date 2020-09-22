package com.lh.wanandroid.ui.setting

import android.os.Bundle
import com.lh.wanandroid.R
import com.lh.wanandroid.ui.BasePreferenceFragment

class AutoNightModeFragment : BasePreferenceFragment() {

    companion object{
        fun get() = AutoNightModeFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pre_autonight)
    }
}