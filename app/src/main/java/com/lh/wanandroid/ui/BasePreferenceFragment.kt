package com.lh.wanandroid.ui

import android.annotation.SuppressLint
import androidx.preference.*
import androidx.recyclerview.widget.RecyclerView

/**
 * 去除PreferenceScreen 左边白边，原因在于为icon留了位置
 */
abstract class BasePreferenceFragment: PreferenceFragmentCompat() {

    private fun setAllPreferenceToAvoidHavingExtraSpacing(preference: Preference){
        preference.isIconSpaceReserved = false
        if (preference is PreferenceGroup){
            for (i in 0 until preference.preferenceCount){
                setAllPreferenceToAvoidHavingExtraSpacing(preference.getPreference(i))
            }
        }
    }

    override fun setPreferenceScreen(preferenceScreen: PreferenceScreen?) {
        preferenceScreen?.let {
            setAllPreferenceToAvoidHavingExtraSpacing(it)
        }
        super.setPreferenceScreen(preferenceScreen)
    }

    override fun onCreateAdapter(preferenceScreen: PreferenceScreen?) = object :
        PreferenceGroupAdapter(preferenceScreen) {

        @SuppressLint("RestrictedApi")
        override fun onPreferenceHierarchyChange(preference: Preference?) {
            preference?.let {
                setAllPreferenceToAvoidHavingExtraSpacing(it)
            }
            super.onPreferenceHierarchyChange(preference)
        }

    }
}