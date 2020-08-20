package com.lh.wanandroid.utils

import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.core.graphics.alpha
import com.lh.wanandroid.R
import com.lh.wanandroid.app.App

/**
 *@author: lh
 *CreateDate: 2020/8/20
 */
object SettingUtil {

    private val setting = PreferenceManager.getDefaultSharedPreferences(App.context)

    /** 获取是否开启夜间模式 **/
    fun getNightModeStatus(): Boolean{
        return setting.getBoolean("nightModeStatus", false)
    }
    
    /** 设置夜间模式 **/
    fun setNightModeStatus(status: Boolean){
        setting.edit {
            putBoolean("nightModeStatus", status)
        }
    }

    /** 获取主题色 **/
    fun getColor(): Int{
        val defaultColor = App.context.resources.getColor(R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && color.alpha  != 255){
            defaultColor
        }else
            color

    }

    /** 设置主题色 **/
    fun setColor(color: Int){
        setting.edit {
            putInt("color", color)
        }
    }
}