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

    /** 获取是否开启自动切换夜间模式 **/
    fun getIsAutoNightMode() = setting.getBoolean("switchOfAutoNightMode", false)

    /** 获取是否开启无图模式 默认是加载的**/
    fun getIsNotLoadImageWithoutWifi() = setting.getBoolean("switchOfImageMode", false)

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

    /** 首页是否显示top文章 **/
    fun getIsShowTopArticle() = setting.getBoolean("switchOfTopArticle", false);

    /** autoNightModel`s start hours of dayModel**/
    fun getHoursOfDayModel() = setting.getString("startHoursOfDayModel", "06").toString()

    fun getMinuteOfDayModel() = setting.getString("startMinuteOfDayModel", "00").toString()

    /** autoNightModel`s start hours of nightModel**/
    fun getHoursOfNightModel() = setting.getString("startHoursOfNightModel", "22").toString()

    fun getMinuteOfNightModel() = setting.getString("startMinuteOfNightModel", "00").toString()


    fun setHoursOfDayModel(data: String) = setting.edit {
        putString("startHoursOfDayModel", data)
    }

    fun setMinuteOfDayModel(data: String) = setting.edit {
        putString("startMinuteOfDayModel", data)
    }

    fun setHoursOfNightModel(data: String) = setting.edit {
        putString("startHoursOfNightModel", data)
    }

    fun setMinuteOfNightModel(data: String) = setting.edit {
        putString("startMinuteOfNightModel", data)
    }


}